<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
    <script src ="/js/page-change.js"></script>
    <style>
        table, tr, td, th{
            border : 1px solid black;
            border-collapse: collapse;
            padding : 5px 10px;
            text-align: center;
        }
        th{
            background-color: #a0ceff;
        }
        tbody td:first-child{
             background-color: #cce5ff;
        }
        #prof-add{
            margin-left: 464px;
        }
        button{
            box-shadow: 1px 1px 2px gray;
            margin-top: 10px;
        }
        select{
            box-shadow: 1px 1px 2px gray;
            margin-bottom: 10px;
        }
        .selected-row {
        background-color: #cce5ff; 
        }
        #index, a{
            text-align: center;
            color: blue;
            margin: 2px 5px;
            text-decoration: none;
        }
        #index .active{
            font-weight: bold;
            color: red;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
         <div>
            <div class="search-area">
                    직급 : 
                    <select v-model="position" @change="fnGetList()">
                        <option value="">:: 전체 ::</option>
                        <option value="전임강사">전임강사</option>
                        <option value="정교수">정교수</option>
                        <option value="조교수">조교수</option>
                    </select>
                    학과 : <select v-model="deptNo" @change="fnGetList()">
                            <option value="">:: 전체 ::</option>
                            <option v-for="item in deptList" :value="item.deptNo">{{item.dName}}</option>
                    </select>
                    <select v-model="pageSize" @change="currentPage = 1;  fnGetList();"> <!--2개 이상의 함수 선언시 ;으로 구분-->
                        <option value="5">5개씩</option>
                        <option value="10">10개씩</option> 
                        <option value="15">15개씩</option>
                        <option value="20">20개씩</option>
                    </select>
                </div>
            <table>
                <tr>
                    <th>선택</th>
                    <th>교수번호</th>
                    <th>이름</th>
                    <th>직급</th>
                    <th>급여</th>
                    <th>학부</th>
                    <th>학과</th>
                </tr>
                <!-- 직급별 셀렉트 박스 & 학과 -->
                
                <tr v-for="item in list" :class="{ 'selected-row': profNo === item.profNo }">
                    <td><input type="radio" name="remove-btn" v-model="profNo" :value="item.profNo"></td>
                    <td>{{item.profNo}}</td>
                    <td>
                        <a href="javascript:;" @click="fnProfView(item.profNo)">{{item.name}}</a>
                    </td>
                    <td>{{item.position}}</td>
                    <td>{{item.pay}}</td>
                    <td>{{item.dName2}}</td>
                    <td>{{item.dName3}}</td>
                </tr>
            </table>
            <div>
                <a v-if="currentPage != 1" href="javascript:;" @click="currentPage -= 1; fnGetList();">◀</a>
                <a href="javascript:;" v-for="num in index" id="index" @click="fnPage(num)">
                    <span :class="{active :  currentPage == num}">{{num}}</span>
                </a>
                <a v-if="currentPage != index" href="javascript:;" @click="currentPage += 1; fnGetList();">▶</a>
            </div>
            <div class="btn-area">
                <button @click="fnProfDelete()">교수 삭제</button>
                <a href="/prof/add.do"><button id="prof-add">교수 추가</button></a>
            </div>
         </div>
    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                list : [],
                deptList : [],
                position : "",
                deptNo : "",
                profNo : "",
                selectedRow: null,

                pageSize : 5, // 한 페이지에 출력할 개수
                index : 1, // 최대 페이지 숫자
                currentPage : 1 // 현재 페이지
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnGetList: function () {
                let self = this;
                let param = {
                    position : self.position,
                    deptNo : self.deptNo,
                    pageSize : self.pageSize,
                    offSet : self.pageSize * (self.currentPage -1) // db에서 건너뛸 개수
                };
                console.log("param:", param); // ← 추가
                console.log("currentPage:", self.currentPage); // ← 추가
                $.ajax({
                    url: "http://localhost:8080/prof/list.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        self.list = data.list;
                        self.deptList = data.deptList;
                        // 인덱스 구하는 법 :  최대 페이지 수 구하는 식
                        self.index = Math.ceil(data.totalCount / self.pageSize); 
                        console.log(data);
                    }
                });
            },
            fnProfDelete: function () {
                let self = this;
                if(!confirm("정말 삭제하시겠습니까?")){
                    return;
                }
                let param = {
                    profNo : self.profNo
                };
                $.ajax({
                    url: "http://localhost:8080/prof/remove.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        alert(data.message);
                        self.profNo = "";
                        self.fnGetList();
                    }
                });
            },
            fnProfView : function(profNo){
                pageChange("/prof/view.do",{profNo : profNo});
            },
            fnPage :  function(page){
                let self = this;
                self.currentPage = page;
                self.fnGetList();
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
            self.fnGetList();
        }
    });

    app.mount('#app');
</script>
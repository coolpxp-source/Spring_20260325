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
            background-color: paleturquoise;
        }
        tbody td:first-child{
             background-color: azure;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
         <div>
            <table>
                <tr>
                    <th>교수번호</th>
                    <th>이름</th>
                    <th>직급</th>
                    <th>급여</th>
                    <th>학부</th>
                    <th>학과</th>
                </tr>
                <!-- 직급별 셀렉트 박스 & 학과 -->
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
                </div>
                <tr v-for="item in list">
                    <td>{{item.profNo}}</td>
                    <td>{{item.name}}</td>
                    <td>{{item.position}}</td>
                    <td>{{item.pay}}</td>
                    <td>{{item.dName2}}</td>
                    <td>{{item.dName3}}</td>
                </tr>
            </table>
            <div>
                <a href="/prof/add.do"><button>교수 추가</button></a>
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
                deptNo : ""
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnGetList: function () {
                let self = this;
                let param = {
                    position : self.position,
                    deptNo : self.deptNo
                };
                $.ajax({
                    url: "http://localhost:8080/prof/list.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        self.list = data.list;
                        self.deptList = data.deptList;
                    }
                });
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
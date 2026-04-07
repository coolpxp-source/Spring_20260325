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
    <style>
        table, tr, td, th{
            border : 1px solid black;
            border-collapse: collapse;
            padding : 5px 10px;
            text-align: center;
        }
        th{
            background-color: lightblue;
        }
        tr:nth-child(even){
            background-color: azure;
        }
        button{
            margin-top: 5px;
            box-shadow: 1px 1px 3px gray;
        }
        select{
            margin: 5px;
            margin-left: 30px;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
         <h1>학생리스트</h1>
        <div>
            <input v-model="keyword">
            <button @click="fnGetList()">검색</button>
            <select v-model="dept" @change="fnGetList()">
                <option value="">:: 학과 선택 ::</option>
                <option value="기계">기계</option>
                <option value="전기전자">전기전자</option>
                <option value="컴퓨터정보">컴퓨터정보</option>
            </select>
            <table>
                <tr>
                    <th>선택</th>
                    <th>학번</th>
                    <th>이름</th>
                    <th>학과</th>
                    <th>학년</th>
                    <th>성별</th>
                    <th>삭제</th>
                </tr>
                <tr v-for="item in list"> <!--view 반복문-->
                    <td><input type="checkbox" v-model="selectList" :value="item.stuNo"></td>
                    <td>{{item.stuNo}}</td>
                    <td>{{item.stuName}}</td>
                    <td>{{item.stuDept}}</td>
                    <td>{{item.stuGrade}}</td>
                    <td>
                        <span v-if="item.stuGender === 'M'">남자</span>
                        <span v-else>여자</span>
                    </td>
                    <td><button @click="fnRemove(item.stuNo)">삭제</button></td>
                </tr>
            </table>
        </div>
        <div>
            <button @click="fnAdd()">학생추가</button>
            <button @click="fnRemoveAll()">삭제</button>
        </div>
    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                list :[],
                selectList : [],
                keyword : "",
                dept : ""
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnGetList: function () {
                let self = this;
                let param = {
                    keyword : self.keyword,
                    dept : self.dept 
                };
                $.ajax({
                    url: "http://localhost:8080/stu-list.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        self.list = data.list;
                    }
                });
            },
            fnRemove: function (stuNo) {
                let self = this;
                let param = {
                    stuNo : stuNo
                };
                $.ajax({
                    url: "http://localhost:8080/stu-remove.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        alert(data.message);
                        self.fnGetList();
                    }
                });
            },
            fnAdd : function(){
                location.href="stu-add.do";
            },
            fnRemoveAll : function(){
                let self = this;
                var fList = JSON.stringify(self.selectList);
                let param = {
                    selectList : fList
                };
                $.ajax({
                    url: "http://localhost:8080/stu-remove-all.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        alert(data.message);
                        self.selectList = [];
                        self.fnGetList();
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
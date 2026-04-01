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
            background-color: #fffecc;
        }
        button{
            margin-top: 10px;
            margin-right: 10px;
            box-shadow: 1px 1px 2px gray;
        }
        .color{
            background-color: #fffecc;
        }
        td{
            width: 150px;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
        <h3>학생 상세 정보 : {{info.dName1}} <span class="color">{{info.name}}</span></h3>
        <table>
            <tr>
                <th>이름 </th>
                <td>{{info.name}}</td>
            </tr>
            <tr>
                <th>학번 </th>
                <td>{{info.stuNo}}</td>
            </tr>
            <tr>
                <th>학년 </th>
                <td>{{info.grade}}</td>
            </tr>
            <tr>
                <th>학과 </th>
                <td>{{info.dName2}}</td>
            </tr>
            <tr>
                <th>아이디 </th>
                <td>{{info.id}}</td>
            </tr>
            <tr>
                <th>담당교수 </th>
                <td>{{info.pName}}</td>
            </tr>
        </table>
        <div>
            <a href="/stu/list.do"><button>목록</button></a>
            <button @click="fnEdit()">수정</button>
        </div>
    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                stuNo : "${map.stuNo}",
                info : {}
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnGetinfo: function () {
                let self = this;
                let param = {
                    stuNo : self.stuNo
                };
                $.ajax({
                    url: "http://localhost:8080/stu/info.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        self.info = data.info;
                    }
                });
            },
            fnEdit : function(){
                pageChange("/stu/edit.do",{stuNo : this.stuNo });
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
            self.fnGetinfo();
        }
    });

    app.mount('#app');
</script>
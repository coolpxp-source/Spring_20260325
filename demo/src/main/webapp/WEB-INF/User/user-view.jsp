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
            background-color: beige;
        }
        tr:nth-child(even){
            background-color: azure;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
         <h3>회원 정보 수정</h3>
         <table>
                <tr>
                    <th>프로필 이미지</th>
                    <td></td>
                </tr>
                <tr v-for="item in list">
                    <th>이름</th>
                    <td>{{item.userName}}</td>
                </tr>
                <tr>
                    <th>아이디</th>
                    <td>{{item.userId}}</td>
                </tr>
                <tr>
                    <th>성별</th>
                    <td>
                        <span v-if="item.gender == 'M'">남자</span>
                        <span v-else-if="item.gender == 'F'">여자</span>
                        <span v-else>미입력</span>
                    </td>
                </tr>
            </table>
            <button @click="fnDelete()">삭제</button>
    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnGetList: function () {
                let self = this;
                let param = {};
                $.ajax({
                    url: "http://localhost:8080/",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {

                    }
                });
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
        }
    });

    app.mount('#app');
</script>
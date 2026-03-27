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
        body{
            box-sizing: border-box;
        }
        #container{
            width: 800px;
            margin: 50px auto;
        }
        .contents{
            width: 750px;
        }
        table, tr, td, th{
            border : 1px solid black;
            border-collapse: collapse;
            padding : 5px 10px;
            text-align: center;
        }
        th{
            background-color: paleturquoise;
            width: 20%;
        }
        td{
            text-align: left;
        }
        table{
            width: 100%;
        }
        select{
            width: 100px;
            height: 25px;
        }
        input{
            width: 90%;
        }
        .title{
            height: 25px;
        }
        .btn{
            margin: 10px;
            text-align: center;
            
        }
        button{
            box-shadow: 1px 1px 2px gray;
            margin: 5px;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
        <div id="container">
            <div class="contents">
                <table>
                    <tr>
                        <th>게시판</th>
                        <td>
                            <select v-model="info.kind">
                                <option value="1">공지사항</option>
                                <option value="2">자유게시판</option>
                                <option value="3">문의게시판</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th  class="title" >제목</th>
                        <td>
                            <input type="text" v-model="info.title">
                        </td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>
                            <textarea v-model="info.contents" cols="72" rows="10"></textarea>
                        </td>
                    </tr>
                </table>
                <div class="btn">
                    <button @click="fnAdd()">작성하기</button>
                    <a href="/board/list.do"><button>되돌아가기</button></a>
                </div>
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
                info : {
                    kind : "1",
                    title : "",
                    contents : ""
                }
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnAdd: function () {
                let self = this;
                let param = self.info;
                $.ajax({
                    url: "http://localhost:8080/board/add.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        alert(data.message);
                        if(data.result == 'success'){ // 성공 메시지 띄우기
                            location.href="/board/list.do";
                        }
                    }
                });
            },
            fnList : function(){
            
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
        }
    });

    app.mount('#app');
</script>
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
        table{
            width: 800px;
        }
        .sub-title{
            font-size: 13px;
            height: 10px;
        }
        .sub-time{
            width: 20%;
        }
        #contents{
            padding: 50px;
            height: auto;
        }
        .bottom-btn{
            margin: 10px auto;
            box-shadow: 1px 1px 2px gray;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
         <div>
            <table>
                <tr>
                    <th colspan="6">제목</th>
                </tr>
                <tr>
                    <td colspan="6">{{info.title}}</td>
                </tr>
                <tr class="sub-title">
                    <th colspan="2">작성자</th>
                    <th colspan="2">조회수</th>
                    <th colspan="2" class="sub-time">작성 시간</th>
                </tr>
                <tr class="sub-title">
                    <td colspan="2">{{info.userId}}</td>
                    <td colspan="2">{{info.cnt}}</td>
                    <td colspan="2" class="sub-time">{{info.cdateTime}}</td>
                </tr>
                <tr>
                    <th colspan="6">내용</th>
                </tr>
                <tr>
                    <td colspan="6" id="contents">{{info.contents}}</td>
                </tr>
            </table>
            <div >
                <button @click="fnEdit()" class="bottom-btn">수정</button>
                <a href="/board/list.do"><button class="bottom-btn">뒤로가기</button></a>
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
                boardNo : "${boardNo}",
                info : {}
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnGetBoard: function () {
                let self = this;
                let param = {
                    boardNo : self.boardNo,
                    kind : "view"
                };
                $.ajax({
                    url: "http://localhost:8080/board/info.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        self.info = data.info;
                    }
                });
            },
            fnEdit: function () {
                let self = this;
                pageChange("/board/edit.do",{boardNo : self.boardNo});
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
            // alert(self.boardNo);
            self.fnGetBoard();
        }
    });

    app.mount('#app');
</script>
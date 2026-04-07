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
         <table>
            <tr>
                <th> 사번 </th>
                <td>{{info.empNo}}</td>
            </tr>
            <tr>
                <th>이름 </th>
                <td>{{info.eName}}</td>
            </tr>
            <tr>
                <th>직급 </th>
                <td>{{info.job}}</td>
            </tr>
            <tr>
                <th>부서이름 </th>
                <td>{{info.dName}}</td>
            </tr>
        </table>
        <a href="/empList.do"><button>목록</button></a>
        <button @click="fnEdit()">수정</button>
        <button @click="fnRemove()">삭제</button>
    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                empNo : "${map.empNo}",
                info : {}
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnGetinfo: function () {
                let self = this;
                let param = {
                    empNo : self.empNo
                };
                $.ajax({
                    url: "http://localhost:8080/empInfo.dox",
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
                pageChange("/emp/edit.do",{empNo : this.empNo });
            },
            fnRemove : function(empNo){
                if(!confirm("정말 삭제하시겠습니까?")){
                     return;
                }
                let self = this;
                let param = {
                    empNo : self.empNo
                };
                $.ajax({
                    url: "http://localhost:8080/empRemove.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        alert(data.message);
                        if(data.result == 'success'){
                            location.href = "/empList.do"; // 목록으로 이동(주소입력)
                        }
                    }
                });
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
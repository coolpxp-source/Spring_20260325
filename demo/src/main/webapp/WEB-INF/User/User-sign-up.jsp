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
        #container{
            align-items: center; 
        }
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
        input{
            width: 100px;
            height: 15px;
            border-radius: 10px;
            border: 1px solid gray;
            margin: 5px auto;
        }
        button{
            margin: 5px;
        }
        .title{
            padding: 5px 20px;
            text-align: left;
        }
        .inputbtn{
            margin-left: 15px;
        }
        .inputbtn2{
            margin-left: 30px;
        }
        .join-btn{
            margin: 5px 20px;
        }
        #join-title{
            text-align: center;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
        <div><h3 id="join-title">회원가입</h3></div>
        <div id="container">
            <div>
                <label><span class="title">아이디 :</span><input v-model="userId" class="inputbtn"></label>
                <button @click="fnCheck()" class="join-btn">중복 체크</button>
            </div>
            <div>
                <label><span class="title">비밀번호 :</span><input type="password" v-model="pwd"></label>
            </div>
            <div>
                <label><span class="title">이름 :</span><input v-model="userName" class="inputbtn2"></label>
            </div>
            <div>
                <label>
                    <span class="title">주소 : </span>
                    <input v-model="addr">
                    <button @click="fnAddr()">검색</button>
                </label>
            </div>
            <button @click="fnJoin()" class="join-btn">가입하기</button>
        </div>
    </div>
</body>
</html>

<script>
    function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
        console.log(window.vueObj);
        window.vueObj.addr = roadFullAddr;
    }
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.

    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                userId : "",
                pwd : "",
                userName : "",
                addr : ""
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnJoin: function () {
                let self = this;
                let param = {
                    userId : self.userId,
                    pwd : self.pwd,
                    userName : self.userName
                };
                $.ajax({
                    url: "http://localhost:8080/join.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        alert(data.message);
                    }
                });
            },
            fnCheck: function () {
                let self = this;
                let param = {
                    userId : self.userId
                };
                $.ajax({
                    url: "http://localhost:8080/check.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        alert(data.message);
                    }
                });
            },
            fnAddr : function(){
                window.open("/addr.do","addr","width=500, height=500"); // 팝업 띄우기
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
            window.vueObj = this; 
        }
    });

    app.mount('#app');
</script>
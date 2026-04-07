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
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
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
         <div>
            <input placeholder="인증하세요">
            <button @click="fnAuth()">인증하기</button>
         </div>
    </div>
</body>
</html>

<script>
    IMP.init("imp03455818"); // 예: 'imp00000000'
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnAuth: function () {
                // IMP.certification(param, callback) 호출
                IMP.certification(
                    {
                        // param
                        channelKey: "channel-key-da6aad37-4f48-4770-b534-9be8b415299e",
                        merchant_uid: "ORD20180131-0000011", // 주문 번호
                        m_redirect_url: "", // 모바일환경에서 popup:false(기본값) 인 경우 필수, 예: https://www.myservice.com/payments/complete/mobile
                        popup: false, // PC환경에서는 popup 파라미터가 무시되고 항상 true 로 적용됨
                    },
                    function (rsp) {
                        // callback
                        console.log(rsp);
                        if (rsp.success) {
                        // 인증 성공 시
                        // jQuery로 HTTP 요청
                        jQuery.ajax({
                            url: "{서버의 인증 정보를 받는 endpoint}",
                            method: "POST",
                            headers: { "Content-Type": "application/json" },
                            data: { imp_uid: rsp.imp_uid },
                        });
                        } else {
                        alert("인증에 실패하였습니다. 에러 내용: " + rsp.error_msg);
                        }
                    },
                );
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
        }
    });

    app.mount('#app');
</script>
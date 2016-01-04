<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Entry</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div id="wrapper">
        <h1>Entry</h1>
        <c:if test="${param.timeout != null}">
            <h3>Session Timeout</h3>
        </c:if>
        <p>Username is ${userName}.</p>
        <a href="${pageContext.request.contextPath}/page">go next</a>
    </div>
</body>
</html>

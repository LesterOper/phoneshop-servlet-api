<%@page contentType="text/html" pageEncoding="windows-1251" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous">
<!DOCTYPE html>
<html>
<body>
<h5>Добро пожаловать</h5>
<h2>
    Мы рады приветствовать Вас на нашем сайте!
    Нажмите на кнопку "Proceed", чтобы перейти на сайт
</h2>
<form method="get" action="${pageContext.servletContext.contextPath}/products">
<button>Proceed</button>
</form>
</body>
</html>

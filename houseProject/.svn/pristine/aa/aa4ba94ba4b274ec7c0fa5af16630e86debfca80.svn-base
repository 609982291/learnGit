<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		//1. 点击 delete 时, 弹出 确定是要删除 xx 的信息吗 ? 若确定, 执行删除, 若不确定, 则取消
		$(".tablelinkdelete").click(function() {
			var lastName = $(this).next(":hidden").val();
			var flag = confirm("确定要删除" + lastName + "的信息吗?");
			if (flag) {
				var $tr = $(this).parent().parent();
				//删除, 使用 ajax 的方式
				var url = this.href;
				var args = {
					"time" : new Date()
				};
				$.post(url, args, function(data) {
					//若 data 的返回值为 1, 则提示 删除成功, 且把当前行删除
					if (data == "1") {
						alert("上传成功!");
						window.location.reload();//刷新当前页面.
					} else {
						//若 data 的返回值不是 1, 提示删除失败. 
						window.location.reload();//刷新当前页面.
						alert(data + "上传成功!");
					}
				});
			}

			//取消超链接的默认行为
			return false;
		});
	})
</script>
</head>
<body>
<s:form action="upload" method="post" enctype="multipart/form-data">
<s:file name="file" label="file">
</s:file>
<s:textfield name="fileDesc" label="fileDesc"></s:textfield>
<s:submit></s:submit>
</s:form>
</body>
</html>
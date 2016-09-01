<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	function validate() {
		var page = document.getElementsByName("page")[0].value;
		if (isNaN(page)) {
			window.location.reload();//刷新当前页面.
			return false;
		}
		if (page > <s:property value="#request.pageBean.totalPage"/>) {
			alert("你输入的页数大于最大页数，页面将跳转到首页！");

			window.document.location.href = "emp-list";

			return false;
		}

		return true;
	}
</script>
<script type="text/javascript">
	$(function() {
		//1. 点击 delete 时, 弹出 确定是要删除 xx 的信息吗 ? 若确定, 执行删除, 若不确定, 则取消
		$(".delete").click(function() {
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
						alert("删除成功!");
						window.location.reload();//刷新当前页面.
					} else {
						//若 data 的返回值不是 1, 提示删除失败. 
						window.location.reload();//刷新当前页面.
						alert(data + "删除失败!");
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
	<s:debug></s:debug>
	<s:if test="#request.pageBean==null||#request.pageBean.list.size()==0">
		没有任何员工信息
	</s:if>
	<s:else>
		<table cellpadding="10" cellspacing="0" border="1">
			<thead>
				<tr>
					<td>ID</td>
					<td>部门名称</td>

					<td>edit</td>
					<td>delete</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.pageBean.list">
					<tr>
						<td>${id}</td>
						<td>${deptName}</td>

						<td><a href="dept-input?id=${id} ">edit</a></td>
						<td><a href="dept-delete?id=${id }" class="delete">Delete</a>
							<input type="hidden" value="${deptName }" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<center>

			<font size="5">共<font color="red"><s:property
						value="#request.pageBean.totalPage" /></font>页
			</font>&nbsp;&nbsp; <font size="5">共<font color="red"><s:property
						value="#request.pageBean.allRows" /></font>条记录
			</font><br> <br>

			<s:if test="#request.pageBean.currentPage == 0">
            首页&nbsp;&nbsp;&nbsp;上一页
        </s:if>

			<s:else>
				<a href="dept-list">首页</a>
            &nbsp;&nbsp;&nbsp;
            <a
					href="dept-list?page=<s:property value="#request.pageBean.currentPage - 1"/>">上一页</a>
			</s:else>

			<s:if
				test="#request.pageBean.currentPage != #request.pageBean.totalPage-1">
				<a
					href="dept-list?page=<s:property value="#request.pageBean.currentPage + 1"/>">下一页</a>
            &nbsp;&nbsp;&nbsp;
            <a
					href="dept-list.action?page=<s:property value="#request.pageBean.totalPage-1"/>">尾页</a>
			</s:if>

			<s:else>
            下一页&nbsp;&nbsp;&nbsp;尾页
        </s:else>

		</center>
		<br>

		<center>

			<form action="dept-list" onsubmit="return validate();">
				<font size="4">跳转至</font> <input type="text" size="2" name="page">页
				<input type="submit" value="跳转">
			</form>

		</center>
		</s:else>
</body>
</html>
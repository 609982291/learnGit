<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>
<script src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$(".click").click(function() {
					window.location.href = "xfdj-input";
				});

				
				$(".tiptop a").click(function() {
					$(".tip").fadeOut(200);
				});

				

				$(".cancel").click(function() {
					$(".tip").fadeOut(100);
				});

			});
</script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">案件处理</a></li>
		</ul>
	</div>

<div class="rightinfo">
	<s:if
		test="#request.underpageBean==null||#request.underpageBean.list.size==0">
		没有任何信息
		</s:if>
	<s:else>

		<table class="tablelist">
			<thead>
				<tr>
					<th>投诉时间</th>
					<th>投诉人姓名</th>
					<th>投诉人电话</th>
					<th>被投诉单位</th>
					<th>单位电话</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.underpageBean.list">
					<tr>
						<td>${complaintDate}</td>
						<td>${caseUserName}</td>
						<td>${caseUserPhone}</td>
						<td>${companyName }</td>
						<td>${companyPhone}</td>
						<td>${caseState }</td>
						<td><a href="under-tohandle?id=${id }" class="tablelink">约谈笔录</a> <input
							type="hidden" value="${id }" />&nbsp;&nbsp;<a
							href="under-authorize?id=${id }" onclick="return confirm('确认外出授权？')" class="tablelinkdelete"
							>外出授权</a>&nbsp;&nbsp; <a
							href="under-notake?id=${id }" onclick="return confirm('确认无法处理？')" class="tablelinkdelete"
							>无法受理</a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:else>

	<div class="pagin">
		<div class="message">
			共<i class="blue"><s:property
					value="#request.underpageBean.allRows" /></i>条记录，当前显示第&nbsp;<i
				class="blue"><s:property
					value="#request.underpageBean.currentPage+1" />&nbsp;</i>页
		</div>
		<ul class="paginList">
			<li class="paginItem"><s:if
					test="#request.underpageBean.currentPage == 0">
					<a href="javascript:;">首页</a>
				</s:if> <s:else>
					<a href="ywsl-list">首页</a>

				</s:else></li>



			<li class="paginItem"><s:if
					test="#request.underpageBean.currentPage == 0">
					<a href="javascript:;">上一页</a>
				</s:if> <s:else>
					<a
						href="under-list?page=<s:property value="#request.underpageBean.currentPage - 1"/>">上一页</a>
				</s:else></li>



			<li class="paginItem"><s:if
					test="#request.underpageBean.currentPage != #request.underpageBean.totalPage-1">
					<a
						href="under-list?page=<s:property value="#request.underpageBean.currentPage + 1"/>">下一页</a>


				</s:if> <s:else>
					<a href="javascript:;">下一页</a>
				</s:else></li>

			<li class="paginItem"><s:if
					test="#request.underpageBean.currentPage != #request.underpageBean.totalPage-1">
					<a
						href="under-list.action?page=<s:property value="#request.underpageBean.totalPage-1"/>">尾页</a>
				</s:if> <s:else>
					<a href="javascript:;">尾页</a>
				</s:else></li>
			<li class="paginItem" style=""><s:form name="ywsl-list?flag=go">
					<s:label value="跳转到"></s:label>
					<s:textfield value="" name="page" id="pageId"></s:textfield>
					<s:label value="页"></s:label>
					<s:submit onclick="return goPage()"></s:submit>
					<script type="text/javascript">
						function goPage() {
							var pageIndex = document.getElementById("pageId").value;//跳转页码
							if (isNaN(pageIndex)) {
								alert("输入的页码无效12");
								return false;
							}
							var totalPage = <s:property value="#request.underpageBean.totalPage"/>;//总页码
							if (pageIndex<=0||pageIndex>totalPage) {
								alert("输入的页码无效");
								return false;
							}
							document.getElementById("pageId").value = pageIndex - 1;
						}
					</script>
				</s:form></li>


		</ul>
	</div>
</div>

	


	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	
	$(function(){
		//1. 点击 delete 时, 弹出 确定是要删除 xx 的信息吗 ? 若确定, 执行删除, 若不确定, 则取消
		$(".tablelinkdelete").click(function(){
			
				
				//删除, 使用 ajax 的方式
				var url = this.href;
				var args = {"time":new Date()};
				$.post(url, args, function(data){
					//若 data 的返回值为 1, 则提示 删除成功, 且把当前行删除
					if(data == "1"){
						alert("延期申请成功!");
						 window.location.reload();//刷新当前页面.
					}
					
					else if(data=="0"){
						//若 data 的返回值不是 1, 提示删除失败. 
						alert("延期申请失败，结案前2天申请延期");
						 window.location.reload();//刷新当前页面.
					}
					else{
						alert(data+"系统错误");
						 window.location.reload();//刷新当前页面.
					}
				});	
			
			
			//取消超链接的默认行为
			return false;
		});		
	})
	
</script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">结案延期</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		
		<s:if
			test="#request.finOrDelaypageBean==null||#request.finOrDelaypageBean.list.size==0">
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
					<s:iterator value="#request.finOrDelaypageBean.list">
						<tr>
							<td>${complaintDate}</td>
							<td>${caseUserName}</td>
							<td>${caseUserPhone}</td>
							<td>${companyName }</td>
							<td>${companyPhone}</td>
							<td><input name="caseState" value="${caseState }"
								type="text" /></td>
							<td><a href="finOrDelay-applyFinish?id=${id }"
								class="tablelink" onclick="return confirm('确定申请结案？')">结案申请</a>&nbsp;&nbsp;<a
								href="finOrDelay-applyDelay?id=${id }" class="tablelinkdelete" onclick="return confirm('确定申请延期？')">延期申请</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:else>

		<div class="pagin">
			<div class="message">
				共<i class="blue"><s:property
						value="#request.finOrDelaypageBean.allRows" /></i>条记录，当前显示第&nbsp;<i
					class="blue"><s:property
						value="#request.finOrDelaypageBean.currentPage+1" />&nbsp;</i>页
			</div>
			<ul class="paginList">
				<li class="paginItem"><s:if
						test="#request.finOrDelaypageBean.currentPage == 0">
						<a href="javascript:;">首页</a>
					</s:if> <s:else>
						<a href="finOrDelay-list">首页</a>

					</s:else></li>



				<li class="paginItem"><s:if
						test="#request.finOrDelaypageBean.currentPage == 0">
						<a href="javascript:;">上一页</a>
					</s:if> <s:else>
						<a
							href="ywsl-list?page=<s:property value="#request.finOrDelaypageBean.currentPage - 1"/>">上一页</a>
					</s:else></li>



				<li class="paginItem"><s:if
						test="#request.finOrDelaypageBean.currentPage != #request.finOrDelaypageBean.totalPage-1">
						<a
							href="ywsl-list?page=<s:property value="#request.finOrDelaypageBean.currentPage + 1"/>">下一页</a>


					</s:if> <s:else>
						<a href="javascript:;">下一页</a>
					</s:else></li>

				<li class="paginItem"><s:if
						test="#request.finOrDelaypageBean.currentPage != #request.finOrDelaypageBean.totalPage-1">
						<a
							href="ywsl-list.action?page=<s:property value="#request.finOrDelaypageBean.totalPage-1"/>">尾页</a>
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
								var pageIndex = document
										.getElementById("pageId").value;//跳转页码
								if (isNaN(pageIndex)) {
									alert("输入的页码无效12");
									return false;
								}
								var totalPage = <s:property value="#request.finOrDelaypageBean.totalPage"/>;//总页码
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


		<div class="tip">

			<div class="tiptop">
				<span>受理投诉</span><a></a>

			</div>
			<s:form action="ywsl-handle" method="post" theme="simple">
				<s:hidden name="id" id="caseID"></s:hidden>
				<div class="tipinfo">
					<ul class="forminfo">
						<li><label>受理人<b>*</b></label>
							<div class="cityleft">
								<s:textfield name="acceptUserId" type="text" class="scinput" />
								&nbsp;&nbsp;&nbsp;
							</div></li>
						<li><label>受理人意见<b>*</b></label>
							<div class="cityright">
								<s:textfield name="acceptUserOp" type="text" class="scinput" />
							</div></li>

					</ul>
				</div>

				<div class="tipbtn">
					<s:submit class="sure" value="确定" />
					&nbsp; <input name="" type="button" class="cancel" value="取消" />
				</div>
			</s:form>
		</div>
	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
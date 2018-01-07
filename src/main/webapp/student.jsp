<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fc" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	response.flushBuffer();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>学生管理页面</title>

<link rel="stylesheet" href="pagination/page.css">
<link href="strap/bootstrap.min.css" rel="stylesheet">

<script src="js/jquery-3.2.1.min.js"></script>
<script src="strap/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="hover/css/style.css" />


<!-- alert2 -->
<script src="alert2/sweetalert2.min.js"></script>
<link href="alert2/sweetalert2.min.css" rel="stylesheet" />
<!-- 多此一举 -->
<!-- <script type="text/javascript">
	$(function(){
	 alert(123);
	 $.post("../../quartz/getStudents.do",$("#searchForm").serialize(),function(data){
		 
	 },"json");
	});
</script> -->
</head>
<body style="background-color: #CCFFFF;">
	<div class="page">
		<section class="demo">
			<nav class="nav">
				<ul>
					<li><a href="function.html"> <span> 主页 </span>
					</a></li>
					<li><a href="javaScript:void(0)" data-toggle="modal"
						data-target="#myModal2"> <span> 导入 </span>
					</a></li>
					<li><a href="http://sc.chinaz.com/"> <span> 统计 </span>
					</a></li>
					<li><a href="javaScript:void(0)" id="deleteByBatch"> <span> 清空 </span>
					</a></li>
					<li><a href="http://sc.chinaz.com/"> <span> Twitter
						</span>
					</a></li>
				</ul>
			</nav>
		</section>
	</div>
	<!-- -----------------------------------------------模态框（Modal）--------------------------------- -->
	<!----------------------------------------------模态框二 用于poi导入-----------------------------------  -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel" align="center">Excel导入</h4>
				</div>
				<div class="modal-body">
					<form class="form-inline" role="form" enctype="multipart/form-data" method="post" 
					action="../../quartz/insertByBatch.do" id="excelForm">
						<div class="form-group" style="padding-left: 200px">
							<label class="sr-only" for="inputfile">文件输入</label> <input
								type="file" id="inputfile" name="excelFile">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="submitExcel">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<script type="text/javascript">
		$("#submitExcel").click(function() {
			$("#excelForm").submit();
		});
	
		
		
	</script>
	<!----------------------------------------------模态框二 用于poi导入-----------------------------------  -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel" align="center">学生信息</h4>
				</div>
				<!-- 表单  -->
				<form role="form" action="../../quartz/updateOrSave.do"
					method="post" id="saveOrUpdateForm">
					<div class="form-group">
						<label for="name">姓名</label> <input type="hidden" name="id"
							id="idUpdate"> <input type="text" class="form-control"
							id="pnameUpdate" placeholder="请输入名称" name="pname">
					</div>
					<div class="form-group">
						<label for="name" >班级</label> <input type="text"
							class="form-control" id="deptUpdate" placeholder="请输入名称"
							name="dept">
					</div>
					<div class="form-group">
						<label for="name">指纹编号</label> <input type="text"
							class="form-control" id="fingerUpdate" placeholder="请输入名称"
							name="finger">
					</div>
					<div class="form-group">
						<label for="name">联系方式</label> <input type="text"
							class="form-control" id="telephoneUpdate" placeholder="请输入名称"
							name="telephone">
					</div>
				</form>
				<!-- 表单  -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						id="updateOrSaveButton">提交更改</button>
				</div>
				<!-- ----------------------------------------- -->
				<script type="text/javascript">
					$("#updateOrSaveButton").click(function() {
						$("#saveOrUpdateForm").submit();
					});
					/* 批量刪除配合 搜索框中的班級名稱進行 */
					$("#deleteByBatch").click(function() {
						var deptName=$("#firstname").val();
						if(deptName==undefined||deptName==""){
							swal(
									  '警告!',
									  "班级不能为空",
									  'error'
									)
						}else{
							location.href="../../quartz/deleteByBatch.do?deptName="+deptName;
						}
					});
				</script>
				<!-- ------------------------------------------ -->
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<!-- -----------------------------------------------------------------模态框（Modal） -->
	<!--                 -------------------搜索区域表单内容  ---------------- ----                           -->
	<br />
	<div class="col-sm-12" style="background-color: #33FFFF;">
		<form class="form-horizontal" role="form" id="searchForm"
			action="../../quartz/getStudents.do" method="post">
			<!--空div  用于让 输入框居中  -->
			<div class="col-sm-2" style="padding-left: 250px">
				<!--添加一名学生  -->
				<input type="button" class="btn btn-default" id="addStudentButton"
					value="添加" />
			</div>
			<!-- ---------------------------------------添加一名学生----------------------------------------------------- -->
			<script type="text/javascript">
				$("#addStudentButton").click(function() {
					$("#myModal").modal('show');
				});
			</script>

			<!-- ---------------------------------------添加一名学生----------------------------------------------------- -->
			<div class="col-sm-3" align="right">
				<label for="firstname" class="col-sm-6 control-label">班级姓名</label>


				<div class="col-sm-6">
					<input type="text" class="form-control" id="firstname"
						placeholder="请输入班级姓名" name="dept" value="${pageBean.dept}">
					<!--  -----------------------   隐藏框 用来做分页-----当前页-----------------------------------              -->
					<input type="hidden" name="curPage" id="curPage"
						value="${pageBean.curPage}">
				</div>
			</div>

			<div class="col-sm-3" align="center">
				<label for="lastname" class="col-sm-6 control-label">学生姓名</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="lastname"
						placeholder="请输入学生姓名" name="pname" value="${pageBean.pname}">
				</div>
			</div>

			<div class="col-sm-2" align="left" style="padding-left: 200px">
				<div class="col-sm-offset-2 col-sm-12">
					<button type="button" class="btn btn-default" id="searchButton">搜索</button>
				</div>
			</div>
			<!--空div  用于让 输入框居中  -->
			<div class="col-sm-2"></div>
		</form>
	</div>
	<script type="text/javascript">
		$("#searchButton").click(function() {
			$("#curPage").val("1");
			$("#searchForm").submit();

		});
	</script>
	<!--               -------------------搜索区域表单内容  ---------------- ----            -->
	<br />
	<!----------------------------------- 学生信息表格内容 -------------------------------------->
	<div class="container  table-responsive">
		<table class="table table-hover">
			<caption align="top" style="padding-left: 500px">
				<font size="4"><b>学生信息</b></font>
			</caption>
			<thead>
				<tr>
					<th align="center">班级名称</th>
					<th align="center">姓名</th>
					<th align="center">指纹编号</th>
					<th align="center">电话号码</th>
					<th align="center">操作</th>
				</tr>
			</thead>
			<tbody >
				<!-- ---------------------------------遍历的位置------------------------------- -->
				<!-- 判断集合长度大于0否  -->
				<c:if test="${fc:length(pageBean.list)>0}">
					<c:forEach items="${pageBean.list}" var="stu">
						<tr>
							<td>${stu.dept}</td>
							<td>${stu.pname}</td>
							<td>${stu.finger}</td>
							<td>${stu.telephone}</td>
							<td><a href="javaScript:void(0)"
								onclick="updateStudent('${stu.id}')">修改</a> ||<a
								href="javaScript:void(0)" onclick="deleteStudent('${stu.id}')">删除</a></td>
						</tr>

					</c:forEach>
				</c:if>

			</tbody>
		</table>
		<script type="text/javascript">
			function updateStudent(id) {
				$("#myModal").modal('show');
				$.post("../../quartz/getStudentById.do", "id=" + id, function(
						data) {
					$("#idUpdate").val(data.id);
					$("#pnameUpdate").val(data.pname);
					$("#deptUpdate").val(data.dept);
					$("#fingerUpdate").val(data.finger);
					$("#telephoneUpdate").val(data.telephone);
				}, "json");
			}
			function deleteStudent(id) {
				var flag = confirm("确定删除？");
				if (flag) {
					location.href = "../../quartz/deleteStudentById.do?id="
							+ id;
				}
			}
		</script>
		<!--  ------------------------------遍历的位置-------------------------------------------  -->
	</div>
	<br>

	<!--分页插件------------------------------------  -->
	<div class="col-sm-12" style="align-content: center">
		<!-- 空的为了让分页条居中 -->
		<div class="col-sm-4"></div>
		<div class="col-sm-5">
			<ul id="menu">
				<li><a href="#" onclick="submitForm('1')">Previous</a></li>
				<c:forEach begin="1" end="${pageBean.totalPage}" var="num">
					<li><a href="#" onclick="submitForm('${num}')">${num}</a></li>
				</c:forEach>
				<!-- <li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">6</a></li>-->
				<li><a href="#" onclick="submitForm('${pageBean.totalPage}')">Next</a></li>
			</ul>
		</div>
		<!-- 空的为了让分页条居中 -->
		<div class="col-sm-3"></div>
	</div>

	<!--分页插件------------------------------------  -->
	<script type="text/javascript">
		function submitForm(num) {
			$("#curPage").val(num);
			$("#searchForm").submit();
		}
	</script>
	<!----------------------------------- 学生信息表格内容 -------------------------------------->
	<div style="text-align: center; clear: both; margin-top: 50px">

		<script src="/gg_bd_ad_720x90-2.js" type="text/javascript"></script>
		<script src="/follow.js" type="text/javascript"></script>
	</div>

</body>
</html>
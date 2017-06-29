<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
       <script>
           function loginOutFun(){
        	   layer.confirm('您确定要退出本系统吗?', {icon: 1, title:'提示'}, function(index){
        	   		  //do something
        	   		  window.location.href="${pageContext.request.contextPath}/logOut";
        	   		  layer.close(main);
        	   });
           }
       </script>
		<!-- BEGIN TOP NAV -->
			<div class="top-navbar">
				<div class="top-navbar-inner">
					
					<!-- Begin Logo brand -->
					<div class="logo-brand">
						长瑞华通项目管理系统
					</div><!-- /.logo-brand -->
					<!-- End Logo brand -->
					
					<div class="top-nav-content">
						
						<!-- Begin button sidebar left toggle -->
						<div class="btn-collapse-sidebar-left">
							<i class="fa fa-bars"></i>
						</div><!-- /.btn-collapse-sidebar-left -->
						<!-- End button sidebar left toggle -->
						
						 
						<!-- End button sidebar right toggle -->
						
						<!-- Begin button nav toggle -->
						<div class="btn-collapse-nav" data-toggle="collapse" data-target="#main-fixed-nav">
							<i class="fa fa-plus icon-plus"></i>
						</div><!-- /.btn-collapse-sidebar-right -->
						<!-- End button nav toggle -->
						
						
						<!-- Begin user session nav -->
						<ul class="nav-user navbar-right">
							<li class="dropdown">
							  <a href="#fakelink" class="dropdown-toggle" data-toggle="dropdown">
								<img src="${pageContext.request.contextPath}/style/admin/assets/img/avatar/avatar-1.jpg" class="avatar img-circle" alt="Avatar">
								欢迎您, <strong>${sessionScope.adminUser.realname}</strong>
							  </a>
							  <ul class="dropdown-menu square primary margin-list-rounded with-triangle">
								<!-- <li><a href="#fakelink">账号管理</a></li>   -->
								<li><a href="javascript:loginOutFun()">退出</a></li>
							  </ul>
							</li>
						</ul>
						<!-- End user session nav -->
						 
						<!-- End Collapse menu nav -->
					</div><!-- /.top-nav-content -->
				</div><!-- /.top-navbar-inner -->
			</div><!-- /.top-navbar -->
			<!-- END TOP NAV -->
			
			<!-- BEGIN SIDEBAR RIGHT HEADING 
		<div class="sidebar-right-heading">
			<ul class="nav nav-tabs square nav-justified">
				<li class="active"><a href="#online-user-sidebar"
					data-toggle="tab"><i class="fa fa-comments"></i></a></li>
				<li><a href="#notification-sidebar" data-toggle="tab"><i
						class="fa fa-bell"></i></a></li>
				<li><a href="#task-sidebar" data-toggle="tab"><i
						class="fa fa-tasks"></i></a></li>
				<li><a href="#setting-sidebar" data-toggle="tab"><i
						class="fa fa-cogs"></i></a></li>
			</ul>
		</div>-->
		<!-- /.sidebar-right-heading -->
		<!-- END SIDEBAR RIGHT HEADING -->
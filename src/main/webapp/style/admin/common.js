/* show message */
function showMessage(msg, bl) {
		$("#msgBoxDIV").fadeIn(100);
		setTimeout(function() {
			$("#msgBoxDIV").fadeOut(1000)
		}, 2000);
		if (bl) {
			$(".msg").css({
				background : "#68af02"
			});
		} else {
			$(".msg").css({
				background : "#ef8f00"
			});
		}
		$(".msg").text((msg + "").replace("Error:", ""));
}
function sendAjax(url, data, callback) {
	layer.load(); 
	$.ajax({
		type : 'POST',
		dataType : "json",
		url : url,
		data : data,
		success : function(data, status, xhr) {
			layer.closeAll('loading');
			if (callback) {
				callback(data);
			}
		},
		error : function(xhq, textStatus, erroThrown) {
			layer.closeAll('loading');
			if (xhq.status == 0) {
				alert("请求失败！");
			} else if (xhq.status == 404) {
				alert("服务器异常！");
			} else if (xhq.status == 500) {
				alert("服务器内部错误！");
			} else if (xhq.status == 200) {
				alert("会话失效，请重新登入");
				window.location.reload(false);
			} else {
				alert("错误：" + xhq.status);
			}
		}
	});
	
}


function sendFileAjax(url, data, callback) {
	layer.load(); 
	$.ajax({
		url: url,
	    type: 'POST',
	    cache: false,
	    data: data,
	    processData: false,
	    contentType: false
	}).done(function(data) {
		layer.closeAll('loading');
		if (callback) {
			callback(data);
		}
	}).fail(function(data) {
		layer.closeAll('loading');
		alert("请求失败！");
	}); 
	
}

 
function openDiv(title,url){
		 layer.open({
			 type: 2,
			 move: false, //关闭拖拽
		     skin: 'layui-layer-rim', //加上边框
		     area: ['65%', '80%'], //宽高
		     title:title,
		     content:url
		});  
}
function trStyleOver(obj){
		$(obj).css("background-color", "#96FED1");
	  }
	  function trStyleOut(obj){
		$(obj).css("background-color", "transparent");
	  }
	function timeStamp2String (time){
		if(time==null||time==""){
			return "";
		}
     var datetime = new Date();
      datetime.setTime(time);
      var year = datetime.getFullYear();
      var month = datetime.getMonth() + 1;
      var date = datetime.getDate();
      var hour = datetime.getHours();
      var minute = datetime.getMinutes();
      var second = datetime.getSeconds();
      var mseconds = datetime.getMilliseconds(); 
      return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
};
function timeStampToDate (time){
	if(time==null||time==""){
		return "";
	}
 var datetime = new Date();
  datetime.setTime(time);
  var year = datetime.getFullYear();
  var month = datetime.getMonth() + 1;
  var date = datetime.getDate();
 
  return year + "-" + month + "-" + date;
};
function initKindEditor(){
	KindEditor.ready(function(K) { 
		var editor1 = K.create('textarea[id="content"]', {
			cssPath : $("#basePath").val()+'/style/admin/kindeditor/plugins/code/prettify.css',
			uploadJson : $("#basePath").val()+'/style/admin/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : $("#basePath").val()+'/style/admin/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : false, 
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					//document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					//document.forms['example'].submit();
				});
			}, 
			afterBlur:function(){ 
	          this.sync(); 
	        }    
		});
		prettyPrint();
	});
}

function uploadHead(){  
	  $.ajaxFileUpload({  
	      url:$("#basePath").val()+"/backgd/file/upLoad",//需要链接到服务器地址   
	      secureuri:false,  
	      fileElementId:"basicInfoHead",//文件选择框的id属性  
	      dataType: 'json',   //json  
	      success: function (data) { 
	         $("#imgHead").attr("src",$("#basePath").val()+"/"+data.imagePath);  
	         $('#basicHeadUrl').val(data.imagePath);  
	      },error:function(XMLHttpRequest, textStatus, errorThrown){  
	     alert('上传失败！');  
	   }  
	  });  
	}
function delData(url) {
	var ids = getIdSelections($('#cusTable'));
	 
	if(ids.length==0){
		layer.msg("请选择要删除的信息！", {icon: 5}); 
	}else{
		layer.confirm('您确定要删除选中的数据吗?', {
			icon : 2,
			title : '提示'
		}, function(index) {  
			sendAjax(url,{"ids":ids+""}, function(data) {
						if (data.success) {
							layer.msg(data.msg, {icon: 1}); 
							initTableData();
						} else {
							layer.msg(data.msg, {icon: 5});
						}
					}
					); 
		});
	}
}
/**
 * 列表页面删除单条数据
 * @param url
 */
function delDataItem(url) {
	 
		layer.confirm('您确定要删除选中的数据吗?', {
			icon : 2,
			title : '提示'
		}, function(index) {  
			sendAjax(url,{}, function(data) { 
						if (data.success) {
							layer.msg(data.msg, {icon: 1}); 
							initTableData();
						} else {
							layer.msg(data.msg, {icon: 5});
						}
					}
					); 
		});
	 
}

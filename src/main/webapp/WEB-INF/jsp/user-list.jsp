<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<table class="easyui-datagrid" id="userList" title="用户列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'user/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60,align:'center'">ID</th>
            <th data-options="field:'userName',width:80,align:'center'">姓名</th>
            <th data-options="field:'password',width:80,align:'center'">密码</th>
            <th data-options="field:'age',width:100,align:'center'">年龄</th>
            <th data-options="field:'email',width:180,align:'center'">邮箱</th>
            <th data-options="field:'phone',width:120,align:'center'">电话</th>
            <th data-options="field:'sex',width:50,align:'center',formatter:TAOTAO.formatSex">性别</th>
            <th data-options="field:'regtime',width:200,align:'center',formatter:TAOTAO.formatDateTime">入职时间</th>
            <th data-options="field:'hiredate',width:200,align:'center',formatter:TAOTAO.formatDate">出生日期</th>
            <th data-options="field:'address',width:200,align:'center'">住址</th>
            <th data-options="field:'degree',width:80,align:'center',formatter:TAOTAO.formatDegree">学历</th>

        </tr>
    </thead>
</table>
<div id="userEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'page/user-edit'" style="width:40%;height:80%;padding:10px;">
</div>
<script>
    $("<table><tr><td style='padding:0 8px'><label>标题:</label></td><td><input id='sTitle' name='sTitle'></td></tr></table>").prependTo("#tabs.panel[style='display: block;'].datagrid-toolbar");

    function getSelectionsIds(){
    	var userList = $("#userList");
    	var sels = userList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'用户名:<input type="text" id="username" name="username" />'
    },{
        text:'查询',
        iconCls:'icon-search',
        handler:function(){
            $("#userList").datagrid("reload",{
                "username" : $("#username").val()
            });
        }
    },'-',{
        text:'重置',
        iconCls:'icon-clear',
        handler:function(){
            $("input[name='username']").val("").focus();
            $("#userList").datagrid("reload",{
                "username" : $("#username").val()
            });
        }
    },'-',{
        text: '新增',
        iconCls: 'icon-add',
        handler: function () {
            $(".tree-title:contains('用户新增')").parent().click();
        }
    },'-',{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条信息才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条信息!');
        		return ;
        	}
        	
        	$("#userEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#userList").datagrid("getSelections")[0];
        			data.priceView = TAOTAO.formatPrice(data.price);
        			$("#userEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },'-',{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中信息!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的信息吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("user/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除信息成功!',undefined,function(){
            					$("#userList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>
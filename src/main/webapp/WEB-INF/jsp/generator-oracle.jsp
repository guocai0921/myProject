<%--
  Created by IntelliJ IDEA.
  User: sungu
  Date: 2018/11/7
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
    <form id="generatorOracleForm" class="generatorOracleForm" method="post">
        <table cellpadding="5">

            <tr>
                <td style="font-weight: bold;color: darkblue">1.数据表生成信息:</td>
            </tr>
            <tr>
                <td style="font-weight: bold;color: darkblue">1.1.表名前缀:</td>
                <td><input class="easyui-textbox" type="text" name="tablePrefixName" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td style="font-weight: bold;color: darkblue">1.2.表名:</td>
                <td><input class="easyui-textbox" type="text" name="tableName" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td style="font-weight: bold;color: darkblue">1.3.包名:</td>
                <td><input class="easyui-textbox" type="text" name="packageName" placeholder="格式:com.guocai.user"
                           data-options="required:true"
                           style="width: 280px;"></input></td>
                <td><span style="color: red">*格式:com.guocai.user</span></td>
            </tr>
            <tr>
                <td style="font-weight: bold;color: darkblue">1.4.实体类名:</td>
                <td><input class="easyui-textbox" type="text" name="entityName" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td style="font-weight: bold;color: darkblue">1.5.创建人:</td>
                <td><input class="easyui-textbox" type="text" name="author" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td style="font-weight: bold;color: #db03d0">2.数据库连接信息:</td>
            </tr>
            <tr>
                <td style="font-weight: bold;color: #db03d0">2.1.Host:</td>
                <td><input id="host" class="easyui-combobox"  name="host" data-options="required:true"
                           style="width: 280px;"></input></td>
                <td><span style="color: red">例如:192.168.1.1</span></td>
            </tr>
            <tr>
                <td style="font-weight: bold;color: #db03d0">2.2.Port:</td>
                <td><input id="port" class="easyui-combobox" name="port" data-options="required:true"
                           style="width: 280px;"></input></td>
                <td><span style="color: red">例如:1521</span></td>
            </tr>
            <tr>
                <td style="font-weight: bold;color: #db03d0">2.3.SID:</td>
                <td><input id ="sid" class="easyui-combobox" name="sid" data-options="required:true"
                           style="width: 280px;"></input></td>
                <td><span style="color: red">例如:orcl</span></td>
            </tr>
            <tr>
                <td style="font-weight: bold;color: #db03d0">2.4.Driver:</td>
                <td><input id ="driver" class="easyui-combobox" name="driver" data-options="required:true"
                           style="width: 280px;"></input></td>
                <td><span style="color: red">例如:thin</span></td>
            </tr>
            </tr>
            <tr>
                <td style="font-weight: bold;color: #db03d0">2.5.数据库用户名:</td>
                <td><input class="easyui-textbox" type="text" name="username" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            </tr>
            <tr>
                <td style="font-weight: bold;color: #db03d0">2.6.数据库密码:</td>
                <td><input class="easyui-textbox" type="text" name="password" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
    </div>
</div>
<script type="text/javascript">

    $(function(){
        $('#host').combobox({
            data: [{code:"127.0.0.1",text:"127.0.0.1"},{code:"10.188.26.39",text:"10.188.26.39"}],
            valueField: 'code',
            textField: 'text'}
        );
        $('#port').combobox({
            data: [{code:"1521",text:"1521"},{code:"1522",text:"1522"}],
            valueField: 'code',
            textField: 'text'}
        );
        $('#sid').combobox({
            data: [{code:"orcl",text:"orcl"},{code:"lg2dbt",text:"lg2dbt"}],
            valueField: 'code',
            textField: 'text'}
        );
        $('#driver').combobox({
            data: [{code:"thin",text:"thin"},{code:"oci",text:"oci"}],
            valueField: 'code',
            textField: 'text'}
        );
    });
        //提交表单
    function submitForm() {
        //有效性验证
        if (!$('#generatorOracleForm').form('validate')) {
            $.messager.alert('提示', '表单还未填写完成!');
            return;
        }

        //ajax的post方式提交表单
        $.post("generator/submit/oracle", $("#generatorOracleForm").serialize(), function (data) {
            if (data.status == 200) {
                $.messager.alert('提示', '生成成功!');
            } else if (data.status == 400) {
                $.messager.alert('提示', data.msg);
            }
        });

    }
</script>

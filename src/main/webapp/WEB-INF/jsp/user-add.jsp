<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
    <form id="userAddForm" class="userForm" enctype="application/json" method="post">
        <table cellpadding="5">
            <tr>
                <td>姓名:</td>
                <td><input class="easyui-validatebox" type="text" name="userName" data-options="required:true"
                           style="width: 200px;"></input></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input class="easyui-validatebox" name="password" data-options="required:true"
                           style="width: 200px;"></input></td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td><input class="easyui-numberbox" type="text" name="age"
                           data-options="min:1,max:200,precision:0," style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input class="easyui-validatebox" type="text" name="email"  validType="email" data-options="required:true"
                           style="width: 200px;"/></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td>
                    <input class="easyui-validatebox" type="text" name="phone" data-options="required:true"
                           style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>性别:</td>
                <td>
                    <input class="easyui-combobox" type="text" id="sex" name="sex" editable="false" data-options="required:true"
                           style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>入职时间:</td>
                <td>
                    <input class="easyui-datetimebox" type="text" id="regtime" name="regtime" data-options="required:true"
                           style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>入职时间:</td>
                <td>
                    <input class="easyui-datebox" type="text" id="hiredate" name="hiredate" data-options="required:true"
                           style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>住址:</td>
                <td>
                    <input class="easyui-validatebox" type="text" id="address" name="address" data-options="required:true"
                            style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>学历:</td>
                <td>
                    <input class="easyui-combobox" type="text" id="degree" name="degree" data-options="required:true"
                           style="width: 200px;"/>
                </td>
            </tr>
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
    </div>
</div>
<script type="text/javascript">

    $(function () {
        $('#sex').combobox({
                data: [{code: "1", text: "男"}, {code: "2", text: "女"}],
                valueField: 'code',
                textField: 'text'
            }
        );
    });
    $(function () {
        $('#degree').combobox({
                data: [
                    {code: "1", text: "小学"},
                    {code: "2", text: "初中"},
                    {code: "3", text: "高中"},
                    {code: "4", text: "大专"},
                    {code: "5", text: "本科"},
                    {code: "6", text: "硕士及以上"}
                ],
                valueField: 'code',
                textField: 'text'
            }
        );
    });

    var itemAddEditor;
    //页面初始化完毕后执行此方法
    $(function () {
        //创建富文本编辑器
        itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=desc]");
        //初始化类目选择和图片上传器
        TAOTAO.init({
            fun: function (node) {
                //根据商品的分类id取商品 的规格模板，生成规格信息
                TAOTAO.changeItemParam(node, "itemAddForm");
            }
        });
    });

    //提交表单
    function submitForm() {
        $.fn.serializeObject = function()
        {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function() {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };

        // ajax的post方式提交表单
        // $("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
        // JSON.stringify($("#userAddForm").serializeObject())
        // $.post("user/save", $("#userAddForm").serialize(), function (data) {
        //     if (data.status == 200) {
        //         $.messager.alert('提示', data.msg);
        //     }
        // });
        $.ajax({
            url: "user/save",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify($("#userAddForm").serializeObject()),
            success: function (data) {
                if(data.status == 200){
                    $('#userAddForm').form('reset');
                    $.messager.alert('提示', data.msg);
                }
        	}
        });
    }
    function clearForm() {
        $('#userAddForm').form('reset');
    }
</script>

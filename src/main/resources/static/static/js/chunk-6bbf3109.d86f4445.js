(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6bbf3109"],{"11e9":function(e,t,a){var r=a("52a7"),o=a("4630"),s=a("6821"),i=a("6a99"),l=a("69a8"),n=a("c69a"),c=Object.getOwnPropertyDescriptor;t.f=a("9e1e")?c:function(e,t){if(e=s(e),t=i(t,!0),n)try{return c(e,t)}catch(a){}if(l(e,t))return o(!r.f.call(e,t),e[t])}},"22ab":function(e,t,a){},"333d":function(e,t,a){"use strict";var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"pagination-container",class:{hidden:e.hidden}},[a("el-pagination",e._b({attrs:{background:e.background,"current-page":e.currentPage,"page-size":e.pageSize,layout:e.layout,"page-sizes":e.pageSizes,total:e.total},on:{"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t},"update:pageSize":function(t){e.pageSize=t},"update:page-size":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}},"el-pagination",e.$attrs,!1))],1)},o=[];a("c5f6");Math.easeInOutQuad=function(e,t,a,r){return e/=r/2,e<1?a/2*e*e+t:(e--,-a/2*(e*(e-2)-1)+t)};var s=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)}}();function i(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}function l(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function n(e,t,a){var r=l(),o=e-r,n=20,c=0;t="undefined"===typeof t?500:t;var u=function e(){c+=n;var l=Math.easeInOutQuad(c,r,o,t);i(l),c<t?s(e):a&&"function"===typeof a&&a()};u()}var c={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(e){this.$emit("update:page",e)}},pageSize:{get:function(){return this.limit},set:function(e){this.$emit("update:limit",e)}}},methods:{handleSizeChange:function(e){this.$emit("pagination",{page:this.currentPage,limit:e}),this.autoScroll&&n(0,800)},handleCurrentChange:function(e){this.$emit("pagination",{page:e,limit:this.pageSize}),this.autoScroll&&n(0,800)}}},u=c,p=(a("e498"),a("2877")),d=Object(p["a"])(u,r,o,!1,null,"6af373ef",null);t["a"]=d.exports},"5dbc":function(e,t,a){var r=a("d3f4"),o=a("8b97").set;e.exports=function(e,t,a){var s,i=t.constructor;return i!==a&&"function"==typeof i&&(s=i.prototype)!==a.prototype&&r(s)&&o&&o(e,s),e}},7456:function(e,t,a){},"8b97":function(e,t,a){var r=a("d3f4"),o=a("cb7c"),s=function(e,t){if(o(e),!r(t)&&null!==t)throw TypeError(t+": can't set as prototype!")};e.exports={set:Object.setPrototypeOf||("__proto__"in{}?function(e,t,r){try{r=a("9b43")(Function.call,a("11e9").f(Object.prototype,"__proto__").set,2),r(e,[]),t=!(e instanceof Array)}catch(o){t=!0}return function(e,a){return s(e,a),t?e.__proto__=a:r(e,a),e}}({},!1):void 0),check:s}},9093:function(e,t,a){var r=a("ce10"),o=a("e11e").concat("length","prototype");t.f=Object.getOwnPropertyNames||function(e){return r(e,o)}},a173:function(e,t,a){"use strict";a("22ab")},aa77:function(e,t,a){var r=a("5ca1"),o=a("be13"),s=a("79e5"),i=a("fdef"),l="["+i+"]",n="​",c=RegExp("^"+l+l+"*"),u=RegExp(l+l+"*$"),p=function(e,t,a){var o={},l=s((function(){return!!i[e]()||n[e]()!=n})),c=o[e]=l?t(d):i[e];a&&(o[a]=c),r(r.P+r.F*l,"String",o)},d=p.trim=function(e,t){return e=String(o(e)),1&t&&(e=e.replace(c,"")),2&t&&(e=e.replace(u,"")),e};e.exports=p},c5f6:function(e,t,a){"use strict";var r=a("7726"),o=a("69a8"),s=a("2d95"),i=a("5dbc"),l=a("6a99"),n=a("79e5"),c=a("9093").f,u=a("11e9").f,p=a("86cc").f,d=a("aa77").trim,m="Number",f=r[m],g=f,b=f.prototype,v=s(a("2aeb")(b))==m,h="trim"in String.prototype,_=function(e){var t=l(e,!1);if("string"==typeof t&&t.length>2){t=h?t.trim():d(t,3);var a,r,o,s=t.charCodeAt(0);if(43===s||45===s){if(a=t.charCodeAt(2),88===a||120===a)return NaN}else if(48===s){switch(t.charCodeAt(1)){case 66:case 98:r=2,o=49;break;case 79:case 111:r=8,o=55;break;default:return+t}for(var i,n=t.slice(2),c=0,u=n.length;c<u;c++)if(i=n.charCodeAt(c),i<48||i>o)return NaN;return parseInt(n,r)}}return+t};if(!f(" 0o1")||!f("0b1")||f("+0x1")){f=function(e){var t=arguments.length<1?0:e,a=this;return a instanceof f&&(v?n((function(){b.valueOf.call(a)})):s(a)!=m)?i(new g(_(t)),a,f):_(t)};for(var w,F=a("9e1e")?c(g):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger".split(","),y=0;F.length>y;y++)o(g,w=F[y])&&!o(f,w)&&p(f,w,u(g,w));f.prototype=b,b.constructor=f,a("2aba")(r,m,f)}},e498:function(e,t,a){"use strict";a("7456")},e92e:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-col",{staticClass:"toolbar",staticStyle:{"padding-bottom":"0px"},attrs:{span:24}},[a("el-form",{attrs:{inline:!0,model:e.listQuery},nativeOn:{submit:function(e){e.preventDefault()}}},[a("el-form-item",{attrs:{label:"用户名:"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"请输入用户名"},model:{value:e.listQuery.userName,callback:function(t){e.$set(e.listQuery,"userName",t)},expression:"listQuery.userName"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"success",size:"mini",icon:"el-icon-search"},on:{click:e.getList}},[e._v("查询")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.handleAdd}},[e._v("添加")])],1)],1)],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"table",staticStyle:{width:"100%"},attrs:{data:e.list,border:""}},[a("af-table-column",{attrs:{prop:"userName",fixed:"left",label:"用户名"}}),e._v(" "),a("af-table-column",{attrs:{prop:"nickname",label:"昵称"}}),e._v(" "),a("af-table-column",{attrs:{prop:"realName",label:"姓名"}}),e._v(" "),a("af-table-column",{attrs:{prop:"phone",label:"电话"}}),e._v(" "),a("af-table-column",{attrs:{prop:"sex",label:"性别"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s({0:"男",1:"女"}[t.row.sex])+"\n      ")]}}])}),e._v(" "),a("af-table-column",{attrs:{prop:"email",label:"邮箱"}}),e._v(" "),a("af-table-column",{attrs:{prop:"userType",label:"用户类型"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s({1:"系统管理员",2:"部门管理员",3:"日志管理员"}[t.row.userType])+"\n      ")]}}])}),e._v(" "),a("af-table-column",{attrs:{prop:"isStop",label:"帐号使用状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",{style:{color:0==t.row.isStop?"#409EFF":"#CC3333"}},[e._v("\n          "+e._s({0:"使用",1:"停用"}[t.row.isStop])+"\n        ")])]}}])}),e._v(" "),a("af-table-column",{attrs:{prop:"isLocked",label:"帐号使用状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",{style:{color:0==t.row.isLocked?"#409EFF":"#CC3333"}},[e._v("\n          "+e._s({0:"未锁住",1:"已锁住"}[t.row.isLocked])+"\n        ")])]}}])}),e._v(" "),a("af-table-column",{attrs:{prop:"neverLogin",label:"是否登录过"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s({0:"未登录过",1:"已登录过"}[t.row.neverLogin])+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"icon",label:"头像"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.icon?a("el-image",{staticClass:"td-img",attrs:{src:t.row.icon,"preview-src-list":e.srcList},on:{click:function(a){return e.vbs(t.row.icon)}}}):e._e()]}}])}),e._v(" "),a("af-table-column",{attrs:{prop:"remark",label:"备注"}}),e._v(" "),a("af-table-column",{attrs:{prop:"timeLastLogin",label:"最后登录时间"}}),e._v(" "),a("af-table-column",{attrs:{prop:"lastLoginIP",label:"最近一次登录IP地址"}}),e._v(" "),a("af-table-column",{attrs:{prop:"timeAdd",label:"创建时间"}}),e._v(" "),a("af-table-column",{attrs:{prop:"timeModify",label:"修改时间"}}),e._v(" "),a("af-table-column",{attrs:{align:"center",fixed:"right",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{staticClass:"btn",attrs:{type:"text",size:"small"},on:{click:function(a){return e.handleEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),a("el-popover",{ref:"popover-"+t.row.id,attrs:{placement:"top",width:"200",trigger:"manual"}},[a("p",[e._v("确定删除该数据吗？")]),e._v(" "),a("div",{staticStyle:{"text-align":"right",margin:"0"}},[a("el-button",{attrs:{size:"mini",type:"text"},on:{click:function(a){e.$refs["popover-"+t.row.id].doClose()}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){return e.handleDel(t.$index,t.row)}}},[e._v("确定")])],1),e._v(" "),a("el-button",{staticClass:"btn",attrs:{slot:"reference",type:"text",size:"small"},on:{click:function(a){e.$refs["popover-"+t.row.id].doShow()}},slot:"reference"},[e._v("删除")])],1),e._v(" "),a("el-button",{staticClass:"btn",attrs:{type:"text",size:"small"},on:{click:function(a){return e.allocationEdit(t.$index,t.row)}}},[e._v("分配角色")]),e._v(" "),a("el-button",{staticClass:"btn",attrs:{type:"text",size:"small"},on:{click:function(a){return e.pwddit(t.$index,t.row)}}},[e._v("重置密码")])]}}])})],1),e._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.listQuery.pageNum,limit:e.listQuery.pageSize},on:{"update:page":function(t){return e.$set(e.listQuery,"pageNum",t)},"update:limit":function(t){return e.$set(e.listQuery,"pageSize",t)},pagination:e.getList}}),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible,"close-on-click-modal":!1},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"editForm",attrs:{model:e.editForm,"label-width":"120px",rules:e.editFormRules}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"用户名称",prop:"userName"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"用户名称"},model:{value:e.editForm.userName,callback:function(t){e.$set(e.editForm,"userName",t)},expression:"editForm.userName"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"昵称",prop:"nickname"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"昵称"},model:{value:e.editForm.nickname,callback:function(t){e.$set(e.editForm,"nickname",t)},expression:"editForm.nickname"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"姓名",prop:"realName"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"姓名"},model:{value:e.editForm.realName,callback:function(t){e.$set(e.editForm,"realName",t)},expression:"editForm.realName"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"电话",prop:"phone"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"电话"},model:{value:e.editForm.phone,callback:function(t){e.$set(e.editForm,"phone",t)},expression:"editForm.phone"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"邮箱",prop:"email"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"邮箱"},model:{value:e.editForm.email,callback:function(t){e.$set(e.editForm,"email",t)},expression:"editForm.email"}})],1),e._v(" "),"create"==e.dialogStatus?a("el-form-item",{staticClass:"form-item",attrs:{label:"登录密码",prop:"password"}},[a("el-input",{staticClass:"filter-item",attrs:{type:"password","auto-complete":"new-password",placeholder:"登录密码"},model:{value:e.editForm.password,callback:function(t){e.$set(e.editForm,"password",t)},expression:"editForm.password"}})],1):e._e(),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"用户类型",prop:"userType"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:e.editForm.userType,callback:function(t){e.$set(e.editForm,"userType",t)},expression:"editForm.userType"}},[a("el-option",{attrs:{value:1,label:"系统管理员"}}),e._v(" "),a("el-option",{attrs:{value:2,label:"部门管理员"}}),e._v(" "),a("el-option",{attrs:{value:3,label:"日志管理员"}})],1)],1),e._v(" "),"update"==e.dialogStatus?a("el-form-item",{staticClass:"form-item",attrs:{label:"帐号使用状态",prop:"isStop"}},[a("el-radio-group",{model:{value:e.editForm.isStop,callback:function(t){e.$set(e.editForm,"isStop",t)},expression:"editForm.isStop"}},[a("el-radio",{attrs:{label:0}},[e._v("使用")]),e._v(" "),a("el-radio",{attrs:{label:1}},[e._v("停用")])],1)],1):e._e()],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"地址",prop:"address"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"地址"},model:{value:e.editForm.address,callback:function(t){e.$set(e.editForm,"address",t)},expression:"editForm.address"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"性别",prop:"sex"}},[a("el-radio-group",{model:{value:e.editForm.sex,callback:function(t){e.$set(e.editForm,"sex",t)},expression:"editForm.sex"}},[a("el-radio",{attrs:{label:0}},[e._v("男")]),e._v(" "),a("el-radio",{attrs:{label:1}},[e._v("女")])],1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"所属部门",prop:"orgID"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:e.editForm.orgID,callback:function(t){e.$set(e.editForm,"orgID",t)},expression:"editForm.orgID"}},e._l(e.orgOptions,(function(e){return a("el-option",{key:e.orgID,attrs:{label:e.orgName,value:e.orgID}})})),1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"头像图片",prop:"headUrl"}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"string","show-file-list":!1,"before-upload":e.handleImagesUrlBefore,"http-request":e.doUpload,"on-success":e.uploadUrl,accept:".jpg,.jpeg,.png,.gif"}},[e.editForm.headUrl?a("img",{staticClass:"avatar",attrs:{src:e.editForm.headUrl}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"备注信息",prop:"remark"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"备注信息"},model:{value:e.editForm.remark,callback:function(t){e.$set(e.editForm,"remark",t)},expression:"editForm.remark"}})],1)],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.createData}},[e._v(e._s("create"==e.dialogStatus?"添加":"编辑"))])],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"分配角色",visible:e.dialogFRoleVisible,"close-on-click-modal":!1,width:"35%"},on:{"update:visible":function(t){e.dialogFRoleVisible=t}}},[a("el-form",{ref:"roleForm",attrs:{model:e.roleForm,"label-width":"120px",rules:e.roleFormRules}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"角色",prop:"roleIds"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"",multiple:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:e.roleForm.roleIds,callback:function(t){e.$set(e.roleForm,"roleIds",t)},expression:"roleForm.roleIds"}},e._l(e.roleList,(function(e){return a("el-option",{key:e.id,attrs:{value:e.id,label:e.name}})})),1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(t){e.dialogFRoleVisible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.createRoleData}},[e._v("提交")])],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"重置密码",visible:e.dialogPwdVisible,"close-on-click-modal":!1,width:"35%"},on:{"update:visible":function(t){e.dialogPwdVisible=t}}},[a("el-form",{ref:"pwdForm",attrs:{model:e.pwdForm,"label-width":"120px",rules:e.pwdFormRules}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"登录密码",prop:"password"}},[a("el-input",{staticClass:"filter-item",attrs:{type:"password","auto-complete":"new-password",placeholder:"登录密码"},model:{value:e.pwdForm.password,callback:function(t){e.$set(e.pwdForm,"password",t)},expression:"pwdForm.password"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"二次确认密码",prop:"password2"}},[a("el-input",{staticClass:"filter-item",attrs:{type:"password","auto-complete":"new-password",placeholder:"要与登录密码保持一致"},model:{value:e.pwdForm.password2,callback:function(t){e.$set(e.pwdForm,"password2",t)},expression:"pwdForm.password2"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(t){e.dialogPwdVisible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.updateRoleData}},[e._v("提交")])],1)],1),e._v(" "),0==e.fistFlag?a("amend-pwd"):e._e()],1)},o=[],s=(a("7f7f"),a("5530")),i=a("365c"),l=a("2f62"),n=a("333d"),c={components:{Pagination:n["a"]},computed:Object(s["a"])({},Object(l["b"])(["sidebar","avatar","name","perms","fistFlag"])),data:function(){var e=this,t=function(e,t,a){var r=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;r.test(t)?a():a(new Error("只能输入5-20个以字母开头、可带数字、“_”、“.”的字串"))},a=function(t,a,r){a!=e.pwdForm.password?r(new Error("两次输入密码不一致!")):r()};return{listQuery:{userName:"",pageNum:1,pageSize:10},list:[],total:0,loading:!0,editForm:{isStop:"",orgID:"",password:"",userName:"",userType:"",address:"",email:"",headUrl:"",nickname:"",phone:"",realName:"",remark:"",sex:""},editFormRules:{userName:[{required:!0,message:"用户名不能为空",trigger:"blur"}],isStop:[{required:!0,message:"使用状态不能为空",trigger:"blur"}],userType:[{required:!0,message:"用户类型不能为空",trigger:"blur"}],password:[{required:!0,message:"密码不能为空",trigger:"blur"}],orgID:[{required:!0,message:"所属部门不能为空",trigger:"blur"}],email:[{type:"email",message:"请输入正确的邮箱",trigger:"blur"}]},dialogStatus:"",textMap:{update:"编辑",create:"新增"},dialogFormVisible:!1,options:[],srcList:[],roleForm:{roleIds:"",userId:""},roleFormRules:{roleIds:[{required:!0,message:"请选择角色",trigger:"blur"}]},dialogFRoleVisible:!1,roleList:[],pwdForm:{password:"",password2:"",status:"",username:"",id:""},pwdFormRules:{password:[{required:!0,message:"登录密码不能为空",trigger:"blur"},{validator:t,trigger:"blur"}],password2:[{required:!0,message:"二次确认密码不能为空",trigger:"blur"},{validator:a,trigger:"blur"}]},dialogPwdVisible:!1,orgOptions:[]}},created:function(){this.getList(),this.orgGetList()},methods:{orgGetList:function(){var e=this;Object(i["c"])("organize/list").then((function(t){e.orgOptions=t.data})).catch((function(t){e.orgOptions=[]}))},getList:function(){var e=this;this.loading=!0,Object(i["c"])("user/page",this.listQuery).then((function(t){e.loading=!1,e.list=t.data.list,e.total=t.data.total})).catch((function(t){e.list=[],e.total=0,e.loading=!1}))},handleAdd:function(){var e=this;this.dialogFormVisible=!0,this.dialogStatus="create",this.editForm={isStop:0,orgID:"",password:"",userName:"",userType:"",address:"",email:"",headUrl:"",nickname:"",phone:"",realName:"",remark:"",sex:0},this.$nextTick((function(){e.$refs["editForm"].clearValidate()}))},allocationEdit:function(e,t){var a=this;this.dialogFRoleVisible=!0,this.roleForm={roleIds:t.roleId,userId:t.id},Object(i["c"])("role/listAll").then((function(e){a.roleList=e.data})).catch((function(e){a.roleList=[]}))},createRoleData:function(){var e=this;this.$refs["roleForm"].validate((function(t){t&&Object(i["c"])("user/updaterole",e.roleForm).then((function(t){200==t.code?(e.getList(),e.dialogFRoleVisible=!1,e.$message.success({message:"提交成功",type:"success"})):e.$message.error(t.message)})).catch((function(t){e.$message.error(t.message)}))}))},pwddit:function(e,t){var a=this;this.$confirm('确认要重置 "'+t.userName+'" 密码吗？',"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(i["c"])("user/resetpwd/"+t.id).then((function(e){200==e.code?a.$message.success({message:"重置密码成功",type:"success"}):a.$message.error(e.message)})).catch((function(e){a.$message.error(e.message)}))})).catch((function(){}))},updateRoleData:function(){var e=this;this.$refs["pwdForm"].validate((function(t){t&&Object(i["c"])("admin/resetpass",e.pwdForm).then((function(t){200==t.code?(e.dialogPwdVisible=!1,e.$message.success({message:"重置密码成功",type:"success"})):e.$message.error(t.message)})).catch((function(t){e.$message.error(t.message)}))}))},handleEdit:function(e,t){var a=this;if(t)for(var r in t)null!=t[r]&&"undefined"!=typeof t[r]||delete t[r];this.dialogStatus="update",this.dialogFormVisible=!0,this.editForm={email:t.email,icon:t.icon,id:t.id,note:t.note,password:t.password,status:t.status,userType:t.userType,username:t.username,nickName:t.nickName},this.$nextTick((function(){a.$refs["editForm"].clearValidate()}))},createData:function(){var e=this;this.$refs.editForm.validate((function(t){t&&Object(i["c"])("user/".concat("create"==e.dialogStatus?"add":"update"),e.editForm).then((function(t){200==t.code?(e.getList(),e.$message.success({message:"create"==e.dialogStatus?"添加成功":"修改成功",type:"success"}),e.dialogFormVisible=!1):e.$message.error(t.message)})).catch((function(t){e.$message.error(t.message)}))}))},handleDel:function(e,t){var a=this;Object(i["c"])("user/delete/"+t.id).then((function(r){a.$notify.success({title:"成功",message:"删除成功"}),a.list.splice(e,1),a.total--,a.$refs["popover-"+t.id].doClose()})).catch((function(e){a.$refs["popover-"+t.id].doClose(),a.$message.error(e.message)}))},handleImagesUrlBefore:function(e){this.editForm.iconUrl=e.iconUrl;var t=e.name.substring(e.name.lastIndexOf(".")+1),a=e.size/1024/1024<2;return"png"!==t&&"jpg"!==t&&"jpeg"!==t&&"JPG"!==t&&"PNG"!==t?(this.$message({message:"只能上传图片（即后缀是.png或.jpg）的文件",type:"warning"}),!1):a?void 0:(this.$message.error("上传图片大小不能超过 2MB!"),!1)},doUpload:function(e){var t=this,a=new FormData;a.append("file",e.file),Object(i["a"])("upload/image",a).then((function(e){t.editForm.headUrl=e.data})).catch((function(e){t.$notify.error({title:"失败",message:e.message})}))},uploadUrl:function(e){this.editForm.headUrl=e.data.url},vbs:function(e){this.srcList=[],this.srcList.push(e)}}},u=c,p=(a("a173"),a("2877")),d=Object(p["a"])(u,r,o,!1,null,"0f0dcaba",null);t["default"]=d.exports},fdef:function(e,t){e.exports="\t\n\v\f\r   ᠎             　\u2028\u2029\ufeff"}}]);
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-8640fe4c"],{"11e9":function(t,e,a){var i=a("52a7"),r=a("4630"),s=a("6821"),o=a("6a99"),n=a("69a8"),l=a("c69a"),c=Object.getOwnPropertyDescriptor;e.f=a("9e1e")?c:function(t,e){if(t=s(t),e=o(e,!0),l)try{return c(t,e)}catch(a){}if(n(t,e))return r(!i.f.call(t,e),t[e])}},"333d":function(t,e,a){"use strict";var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[a("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,"page-sizes":t.pageSizes,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:current-page":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"update:page-size":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},r=[];a("c5f6");Math.easeInOutQuad=function(t,e,a,i){return t/=i/2,t<1?a/2*t*t+e:(t--,-a/2*(t*(t-2)-1)+e)};var s=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)}}();function o(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}function n(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function l(t,e,a){var i=n(),r=t-i,l=20,c=0;e="undefined"===typeof e?500:e;var u=function t(){c+=l;var n=Math.easeInOutQuad(c,i,r,e);o(n),c<e?s(t):a&&"function"===typeof a&&a()};u()}var c={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&l(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&l(0,800)}}},u=c,d=(a("e498"),a("2877")),p=Object(d["a"])(u,i,r,!1,null,"6af373ef",null);e["a"]=p.exports},"5dbc":function(t,e,a){var i=a("d3f4"),r=a("8b97").set;t.exports=function(t,e,a){var s,o=e.constructor;return o!==a&&"function"==typeof o&&(s=o.prototype)!==a.prototype&&i(s)&&r&&r(t,s),t}},"73fd":function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-card",{staticClass:"operate-container",attrs:{shadow:"never"}},[a("el-form",{attrs:{inline:!0,model:t.listQuery},nativeOn:{submit:function(t){t.preventDefault()}}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"盘点状态:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:t.listQuery.status,callback:function(e){t.$set(t.listQuery,"status",e)},expression:"listQuery.status"}},[a("el-option",{attrs:{value:"0",label:"盘点中"}}),t._v(" "),a("el-option",{attrs:{value:"1",label:"盘点结束"}}),t._v(" "),a("el-option",{attrs:{value:"2",label:"盘点取消"}})],1)],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"success",size:"mini",icon:"el-icon-search"},on:{click:t.getList}},[t._v("查询")])],1)],1)],1),t._v(" "),a("div",{staticClass:"btn-btm"},[a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:t.handleAdd}},[t._v("添加")])],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],ref:"table",staticStyle:{width:"100%"},attrs:{data:t.list,border:"","header-cell-style":{background:"#F8F8F9",fontSize:"13px",color:"#515a6e"}}},[a("af-table-column",{attrs:{prop:"orgName",fixed:"left",label:"所属部门名称"}}),t._v(" "),a("af-table-column",{attrs:{prop:"status",label:"盘点状态"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s({0:"盘点中",1:"盘点结束",2:"盘点取消"}[e.row.status])+"\n      ")]}}])}),t._v(" "),a("af-table-column",{attrs:{prop:"description",label:"盘点任务说明"}}),t._v(" "),a("af-table-column",{attrs:{prop:"cancelReason",label:"盘点取消原因"}}),t._v(" "),a("af-table-column",{attrs:{prop:"startDate",label:"计划开始日期"}}),t._v(" "),a("af-table-column",{attrs:{prop:"endDate",label:"计划结束日期"}}),t._v(" "),a("af-table-column",{attrs:{prop:"finishTime",label:"结束或取消时间"}}),t._v(" "),a("af-table-column",{attrs:{prop:"remark",label:"部门备注"}}),t._v(" "),a("af-table-column",{attrs:{prop:"addTime",label:"创建时间"}}),t._v(" "),a("el-table-column",{attrs:{fixed:"right",label:"操作","min-width":"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{staticClass:"btn",attrs:{type:"text",size:"small"},on:{click:function(a){return t.handleEdit(e.$index,e.row)}}},[t._v("编辑")]),t._v(" "),a("el-dropdown",{attrs:{trigger:"click"}},[a("el-button",{attrs:{type:"text",size:"small"}},[t._v("更多")]),t._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("span",{on:{click:function(a){return t.handleDel(e.$index,e.row)}}},[a("el-dropdown-item",[t._v("取消任务")])],1),t._v(" "),a("span",{on:{click:function(a){return t.handleRouter(e.row)}}},[a("el-dropdown-item",[t._v("盘点清单")])],1),t._v(" "),a("span",{on:{click:function(a){return t.handleResults(e.row)}}},[a("el-dropdown-item",[t._v("盘点结果")])],1),t._v(" "),a("span",{on:{click:function(a){return t.handleRun(e.row)}}},[a("el-dropdown-item",[t._v("盘点结果损益确认")])],1)])],1)]}}])})],1),t._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total,page:t.listQuery.pageNum,limit:t.listQuery.pageSize},on:{"update:page":function(e){return t.$set(t.listQuery,"pageNum",e)},"update:limit":function(e){return t.$set(t.listQuery,"pageSize",e)},pagination:t.getList}}),t._v(" "),a("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogFormVisible,"close-on-click-modal":!1,width:"35%"},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"editForm",attrs:{model:t.editForm,rules:t.editFormRules,"label-width":"130px"}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"选择部门:",prop:"orgID"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:t.editForm.orgID,callback:function(e){t.$set(t.editForm,"orgID",e)},expression:"editForm.orgID"}},t._l(t.orgOptions,(function(t){return a("el-option",{key:t.orgID,attrs:{label:t.orgName,value:t.orgID}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"计划开始日期:",prop:"startDate"}},[a("el-date-picker",{staticClass:"filter-item",attrs:{clearable:"",type:"date","value-format":"yyyy-MM-dd",placeholder:"选择计划开始日期"},model:{value:t.editForm.startDate,callback:function(e){t.$set(t.editForm,"startDate",e)},expression:"editForm.startDate"}})],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"计划结束日期:",prop:"endDate"}},[a("el-date-picker",{staticClass:"filter-item",attrs:{clearable:"",type:"date","value-format":"yyyy-MM-dd",placeholder:"选择计划结束日期"},model:{value:t.editForm.endDate,callback:function(e){t.$set(t.editForm,"endDate",e)},expression:"editForm.endDate"}})],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"计划结束日期:",prop:"endDate"}},[a("el-date-picker",{staticClass:"filter-item",attrs:{clearable:"",type:"date","value-format":"yyyy-MM-dd",placeholder:"选择计划结束日期"},model:{value:t.editForm.endDate,callback:function(e){t.$set(t.editForm,"endDate",e)},expression:"editForm.endDate"}})],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"盘点任务说明:",prop:"description\t"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"盘点任务说明",type:"textarea",autosize:{minRows:1,maxRows:4}},model:{value:t.editForm.description,callback:function(e){t.$set(t.editForm,"description",e)},expression:"editForm.description"}})],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取消")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:t.createData}},[t._v("\n        "+t._s("create"==t.dialogStatus?"添加":"保存")+"\n      ")])],1)],1),t._v(" "),a("el-dialog",{attrs:{title:"结束或取消任务",visible:t.statusFormVisible,width:"30%"},on:{"update:visible":function(e){t.statusFormVisible=e}}},[a("el-form",{ref:"statusForm",attrs:{model:t.statusForm,rules:t.statusFormRules,"label-width":"120px"}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"选择类型:",prop:"type"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:t.statusForm.type,callback:function(e){t.$set(t.statusForm,"type",e)},expression:"statusForm.type"}},[a("el-option",{attrs:{value:1,label:"盘点结束"}}),t._v(" "),a("el-option",{attrs:{value:2,label:"盘点取消)"}})],1)],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"盘点取消原因:",prop:"cancelReason"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"盘点取消原因",type:"textarea",autosize:{minRows:1,maxRows:4}},model:{value:t.statusForm.cancelReason,callback:function(e){t.$set(t.statusForm,"cancelReason",e)},expression:"statusForm.cancelReason"}})],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(e){t.statusFormVisible=!1}}},[t._v("取消")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:t.createStatus}},[t._v("提交")])],1)],1),t._v(" "),a("el-dialog",{attrs:{title:"盘点结果损益确认",visible:t.confirmFormVisible,width:"35%"},on:{"update:visible":function(e){t.confirmFormVisible=e}}},[a("el-form",{ref:"confirmForm",attrs:{model:t.confirmForm,rules:t.confirmFormRules,"label-width":"140px"}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"选择需要确认资产:",prop:"assetIDs"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"",multiple:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:t.confirmForm.assetIDs,callback:function(e){t.$set(t.confirmForm,"assetIDs",e)},expression:"confirmForm.assetIDs"}},t._l(t.assetOptions,(function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})})),1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(e){t.confirmFormVisible=!1}}},[t._v("取消")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:t.confirmStatus}},[t._v("提交")])],1)],1),t._v(" "),0==t.fistFlag?a("amend-pwd"):t._e()],1)},r=[],s=a("5530"),o=a("365c"),n=a("333d"),l=a("2f62"),c={name:"inventory",components:{Pagination:n["a"]},computed:Object(s["a"])({},Object(l["b"])(["name","fistFlag"])),data:function(){return{listQuery:{status:""},list:[],loading:!0,total:0,dialogStatus:"",textMap:{update:"编辑",create:"新增"},dialogFormVisible:!1,editForm:{endDate:"",orgID:"",startDate:"",description:""},editFormRules:{endDate:[{required:!0,message:"计划结束日期不能为空",trigger:"blur"}],orgID:[{required:!0,message:"请选择所属部门",trigger:"blur"}],startDate:[{required:!0,message:"请选择计划开始日期",trigger:"blur"}]},orgOptions:[],statusFormVisible:!1,statusForm:{type:"",id:"",cancelReason:""},statusFormRules:{type:[{required:!0,message:"请选择结束或取消类型",trigger:"blur"}],cancelReason:[{required:!0,message:"盘点取消原因不能为空",trigger:"blur"}]},confirmForm:{id:"",assetIDs:""},confirmFormVisible:!1,confirmFormRules:{assetIDs:[{required:!0,message:"请选择需要确认资产",trigger:"blur"}]},assetOptions:[]}},created:function(){this.getList(),this.orgGetList(),this.assetGetList()},methods:{getList:function(){var t=this;this.loading=!0,Object(o["c"])("invetorytask/page",this.listQuery).then((function(e){t.loading=!1,t.list=e.data.list,t.total=e.data.total})).catch((function(e){t.loading=!1,t.list=[],t.total=0}))},handleAdd:function(){var t=this;this.dialogFormVisible=!0,this.dialogStatus="create",this.editForm={endDate:"",orgID:"",startDate:"",description:""},this.$nextTick((function(){t.$refs["editForm"].clearValidate()}))},handleEdit:function(t,e){var a=this;if(e)for(var i in e)null!=e[i]&&"undefined"!=typeof e[i]||delete e[i];this.dialogStatus="update",this.dialogFormVisible=!0,this.editForm=Object.assign({},e),this.$nextTick((function(){a.$refs["editForm"].clearValidate()}))},createData:function(){var t=this;this.$refs.editForm.validate((function(e){e&&Object(o["c"])("invetorytask/".concat("create"==t.dialogStatus?"add":"update"),t.editForm).then((function(e){200==e.code?(t.getList(),t.$message.success({message:"create"==t.dialogStatus?"添加成功":"修改成功",type:"success"}),t.dialogFormVisible=!1):t.$message.error(e.message)})).catch((function(e){t.$message.error(e.message)}))}))},handleDel:function(t,e){var a=this;this.statusFormVisible=!0,this.statusForm={type:"",cancelReason:"",id:e.id},this.$nextTick((function(){a.$refs["statusForm"].clearValidate()}))},createStatus:function(){var t=this;this.$refs["statusForm"].validate((function(e){e&&Object(o["c"])("invetorytask/cancel",t.statusForm).then((function(e){200==e.code?(t.statusFormVisible=!1,t.getList(),t.$message.success({message:"提交成功",type:"success"})):t.$message.error(e.message)})).catch((function(e){t.$message.error(e.message)}))}))},handleRun:function(t){var e=this;this.confirmFormVisible=!0,this.confirmForm={id:t.id,assetIDs:""},this.$nextTick((function(){e.$refs["confirmForm"].clearValidate()}))},confirmStatus:function(){var t=this;this.$refs["confirmForm"].validate((function(e){e&&Object(o["c"])("invetoryasset/confirm",t.confirmForm).then((function(e){200==e.code?(t.confirmFormVisible=!1,t.getList(),t.$message.success({message:"提交成功",type:"success"})):t.$message.error(e.message)})).catch((function(e){t.$message.error(e.message)}))}))},handleRouter:function(t){this.$router.push({name:"listing",params:{id:t.id}})},handleResults:function(t){this.$router.push({name:"results",params:{id:t.id}})},orgGetList:function(){var t=this;Object(o["c"])("organize/list").then((function(e){t.orgOptions=e.data})).catch((function(e){t.orgOptions=[]}))},assetGetList:function(){var t=this;Object(o["c"])("asset/list").then((function(e){t.assetOptions=e.data})).catch((function(e){t.assetOptions=[]}))}}},u=c,d=a("2877"),p=Object(d["a"])(u,i,r,!1,null,"74f135b9",null);e["default"]=p.exports},7456:function(t,e,a){},"8b97":function(t,e,a){var i=a("d3f4"),r=a("cb7c"),s=function(t,e){if(r(t),!i(e)&&null!==e)throw TypeError(e+": can't set as prototype!")};t.exports={set:Object.setPrototypeOf||("__proto__"in{}?function(t,e,i){try{i=a("9b43")(Function.call,a("11e9").f(Object.prototype,"__proto__").set,2),i(t,[]),e=!(t instanceof Array)}catch(r){e=!0}return function(t,a){return s(t,a),e?t.__proto__=a:i(t,a),t}}({},!1):void 0),check:s}},9093:function(t,e,a){var i=a("ce10"),r=a("e11e").concat("length","prototype");e.f=Object.getOwnPropertyNames||function(t){return i(t,r)}},aa77:function(t,e,a){var i=a("5ca1"),r=a("be13"),s=a("79e5"),o=a("fdef"),n="["+o+"]",l="​",c=RegExp("^"+n+n+"*"),u=RegExp(n+n+"*$"),d=function(t,e,a){var r={},n=s((function(){return!!o[t]()||l[t]()!=l})),c=r[t]=n?e(p):o[t];a&&(r[a]=c),i(i.P+i.F*n,"String",r)},p=d.trim=function(t,e){return t=String(r(t)),1&e&&(t=t.replace(c,"")),2&e&&(t=t.replace(u,"")),t};t.exports=d},c5f6:function(t,e,a){"use strict";var i=a("7726"),r=a("69a8"),s=a("2d95"),o=a("5dbc"),n=a("6a99"),l=a("79e5"),c=a("9093").f,u=a("11e9").f,d=a("86cc").f,p=a("aa77").trim,m="Number",f=i[m],g=f,b=f.prototype,v=s(a("2aeb")(b))==m,h="trim"in String.prototype,y=function(t){var e=n(t,!1);if("string"==typeof e&&e.length>2){e=h?e.trim():p(e,3);var a,i,r,s=e.charCodeAt(0);if(43===s||45===s){if(a=e.charCodeAt(2),88===a||120===a)return NaN}else if(48===s){switch(e.charCodeAt(1)){case 66:case 98:i=2,r=49;break;case 79:case 111:i=8,r=55;break;default:return+e}for(var o,l=e.slice(2),c=0,u=l.length;c<u;c++)if(o=l.charCodeAt(c),o<48||o>r)return NaN;return parseInt(l,i)}}return+e};if(!f(" 0o1")||!f("0b1")||f("+0x1")){f=function(t){var e=arguments.length<1?0:t,a=this;return a instanceof f&&(v?l((function(){b.valueOf.call(a)})):s(a)!=m)?o(new g(y(e)),a,f):y(e)};for(var F,_=a("9e1e")?c(g):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger".split(","),w=0;_.length>w;w++)r(g,F=_[w])&&!r(f,F)&&d(f,F,u(g,F));f.prototype=b,b.constructor=f,a("2aba")(i,m,f)}},e498:function(t,e,a){"use strict";a("7456")},fdef:function(t,e){t.exports="\t\n\v\f\r   ᠎             　\u2028\u2029\ufeff"}}]);
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3d283848"],{"11e9":function(e,t,a){var r=a("52a7"),i=a("4630"),l=a("6821"),o=a("6a99"),s=a("69a8"),n=a("c69a"),c=Object.getOwnPropertyDescriptor;t.f=a("9e1e")?c:function(e,t){if(e=l(e),t=o(t,!0),n)try{return c(e,t)}catch(a){}if(s(e,t))return i(!r.f.call(e,t),e[t])}},"333d":function(e,t,a){"use strict";var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"pagination-container",class:{hidden:e.hidden}},[a("el-pagination",e._b({attrs:{background:e.background,"current-page":e.currentPage,"page-size":e.pageSize,layout:e.layout,"page-sizes":e.pageSizes,total:e.total},on:{"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t},"update:pageSize":function(t){e.pageSize=t},"update:page-size":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}},"el-pagination",e.$attrs,!1))],1)},i=[];a("c5f6");Math.easeInOutQuad=function(e,t,a,r){return e/=r/2,e<1?a/2*e*e+t:(e--,-a/2*(e*(e-2)-1)+t)};var l=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)}}();function o(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}function s(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function n(e,t,a){var r=s(),i=e-r,n=20,c=0;t="undefined"===typeof t?500:t;var u=function e(){c+=n;var s=Math.easeInOutQuad(c,r,i,t);o(s),c<t?l(e):a&&"function"===typeof a&&a()};u()}var c={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(e){this.$emit("update:page",e)}},pageSize:{get:function(){return this.limit},set:function(e){this.$emit("update:limit",e)}}},methods:{handleSizeChange:function(e){this.$emit("pagination",{page:this.currentPage,limit:e}),this.autoScroll&&n(0,800)},handleCurrentChange:function(e){this.$emit("pagination",{page:e,limit:this.pageSize}),this.autoScroll&&n(0,800)}}},u=c,d=(a("e498"),a("2877")),p=Object(d["a"])(u,r,i,!1,null,"6af373ef",null);t["a"]=p.exports},"5dbc":function(e,t,a){var r=a("d3f4"),i=a("8b97").set;e.exports=function(e,t,a){var l,o=t.constructor;return o!==a&&"function"==typeof o&&(l=o.prototype)!==a.prototype&&r(l)&&i&&i(e,l),e}},7456:function(e,t,a){},"8b97":function(e,t,a){var r=a("d3f4"),i=a("cb7c"),l=function(e,t){if(i(e),!r(t)&&null!==t)throw TypeError(t+": can't set as prototype!")};e.exports={set:Object.setPrototypeOf||("__proto__"in{}?function(e,t,r){try{r=a("9b43")(Function.call,a("11e9").f(Object.prototype,"__proto__").set,2),r(e,[]),t=!(e instanceof Array)}catch(i){t=!0}return function(e,a){return l(e,a),t?e.__proto__=a:r(e,a),e}}({},!1):void 0),check:l}},9093:function(e,t,a){var r=a("ce10"),i=a("e11e").concat("length","prototype");t.f=Object.getOwnPropertyNames||function(e){return r(e,i)}},aa77:function(e,t,a){var r=a("5ca1"),i=a("be13"),l=a("79e5"),o=a("fdef"),s="["+o+"]",n="​",c=RegExp("^"+s+s+"*"),u=RegExp(s+s+"*$"),d=function(e,t,a){var i={},s=l((function(){return!!o[e]()||n[e]()!=n})),c=i[e]=s?t(p):o[e];a&&(i[a]=c),r(r.P+r.F*s,"String",i)},p=d.trim=function(e,t){return e=String(i(e)),1&t&&(e=e.replace(c,"")),2&t&&(e=e.replace(u,"")),e};e.exports=d},c5f6:function(e,t,a){"use strict";var r=a("7726"),i=a("69a8"),l=a("2d95"),o=a("5dbc"),s=a("6a99"),n=a("79e5"),c=a("9093").f,u=a("11e9").f,d=a("86cc").f,p=a("aa77").trim,m="Number",f=r[m],b=f,g=f.prototype,v=l(a("2aeb")(g))==m,h="trim"in String.prototype,y=function(e){var t=s(e,!1);if("string"==typeof t&&t.length>2){t=h?t.trim():p(t,3);var a,r,i,l=t.charCodeAt(0);if(43===l||45===l){if(a=t.charCodeAt(2),88===a||120===a)return NaN}else if(48===l){switch(t.charCodeAt(1)){case 66:case 98:r=2,i=49;break;case 79:case 111:r=8,i=55;break;default:return+t}for(var o,n=t.slice(2),c=0,u=n.length;c<u;c++)if(o=n.charCodeAt(c),o<48||o>i)return NaN;return parseInt(n,r)}}return+t};if(!f(" 0o1")||!f("0b1")||f("+0x1")){f=function(e){var t=arguments.length<1?0:e,a=this;return a instanceof f&&(v?n((function(){g.valueOf.call(a)})):l(a)!=m)?o(new b(y(t)),a,f):y(t)};for(var _,F=a("9e1e")?c(b):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger".split(","),S=0;F.length>S;S++)i(b,_=F[S])&&!i(f,_)&&d(f,_,u(b,_));f.prototype=g,g.constructor=f,a("2aba")(r,m,f)}},e498:function(e,t,a){"use strict";a("7456")},f6a9:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-card",{staticClass:"operate-container",attrs:{shadow:"never"}},[a("el-form",{attrs:{inline:!0,model:e.listQuery},nativeOn:{submit:function(e){e.preventDefault()}}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"阅读器名称"}},[a("el-input",{staticClass:"filter-item",attrs:{clearable:"",placeholder:"阅读器名称"},model:{value:e.listQuery.readerName,callback:function(t){e.$set(e.listQuery,"readerName",t)},expression:"listQuery.readerName"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"在线状态:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:e.listQuery.lastStatus,callback:function(t){e.$set(e.listQuery,"lastStatus",t)},expression:"listQuery.lastStatus"}},[a("el-option",{attrs:{value:"0",label:"在线"}}),e._v(" "),a("el-option",{attrs:{value:"1",label:"不在线"}})],1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"IP地址:"}},[a("el-input",{staticClass:"filter-item",attrs:{clearable:"",placeholder:"IP地址"},model:{value:e.listQuery.readerIP,callback:function(t){e.$set(e.listQuery,"readerIP",t)},expression:"listQuery.readerIP"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"所在位置:"}},[a("el-input",{staticClass:"filter-item",attrs:{clearable:"",placeholder:"所在位置"},model:{value:e.listQuery.address,callback:function(t){e.$set(e.listQuery,"address",t)},expression:"listQuery.address"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"success",size:"mini",icon:"el-icon-search"},on:{click:e.getList}},[e._v("查询")])],1)],1)],1),e._v(" "),a("div",{staticClass:"btn-btm"},[a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.handleAdd}},[e._v("添加")])],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"table",staticStyle:{width:"100%"},attrs:{data:e.list,border:"","header-cell-style":{background:"#F8F8F9",fontSize:"13px",color:"#515a6e"}}},[a("af-table-column",{attrs:{prop:"readerName",fixed:"left",label:"阅读器名称"}}),e._v(" "),a("af-table-column",{attrs:{prop:"hasAlarm",label:"是否连接报警器"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s({0:"否",1:"是"}[t.row.hasAlarm])+"\n      ")]}}])}),e._v(" "),a("af-table-column",{attrs:{prop:"address",label:"所在位置"}}),e._v(" "),a("af-table-column",{attrs:{prop:"brand",label:"品牌"}}),e._v(" "),a("af-table-column",{attrs:{prop:"checkTime",label:"最后在线检测时间"}}),e._v(" "),a("af-table-column",{attrs:{prop:"model",label:"设备型号"}}),e._v(" "),a("af-table-column",{attrs:{prop:"readerIP",label:"阅读器IP地址"}}),e._v(" "),a("af-table-column",{attrs:{prop:"interfaceType",label:"接口分类"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s({0:"有源(M5)",1:"有源(自有)",2:"无源(1天线)",3:"无源(2天线)",4:"无源(3天线)",5:"无源(4天线)"}[t.row.interfaceType])+"\n      ")]}}])}),e._v(" "),a("af-table-column",{attrs:{prop:"lastStatus",label:"最后在线状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s({0:"在线",1:"不在线"}[t.row.lastStatus])+"\n      ")]}}])}),e._v(" "),a("af-table-column",{attrs:{prop:"remark",label:"备注"}}),e._v(" "),a("af-table-column",{attrs:{prop:"setupDate",label:"安装日期"}}),e._v(" "),a("af-table-column",{attrs:{prop:"timeAdd",label:"加入时间"}}),e._v(" "),a("el-table-column",{attrs:{fixed:"right",label:"操作","min-width":"120"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{staticClass:"btn",attrs:{type:"text",size:"small"},on:{click:function(a){return e.handleEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),a("el-popover",{ref:"popover-"+t.row.id,attrs:{placement:"top",width:"200",trigger:"manual"}},[a("p",[e._v("确定删除该数据吗？")]),e._v(" "),a("div",{staticStyle:{"text-align":"right",margin:"0"}},[a("el-button",{attrs:{size:"mini",type:"text"},on:{click:function(a){e.$refs["popover-"+t.row.id].doClose()}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){return e.handleDel(t.$index,t.row)}}},[e._v("确定")])],1),e._v(" "),a("el-button",{staticClass:"btn",attrs:{slot:"reference",type:"text",size:"small"},on:{click:function(a){e.$refs["popover-"+t.row.id].doShow()}},slot:"reference"},[e._v("删除")])],1)]}}])})],1),e._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.listQuery.pageNum,limit:e.listQuery.pageSize},on:{"update:page":function(t){return e.$set(e.listQuery,"pageNum",t)},"update:limit":function(t){return e.$set(e.listQuery,"pageSize",t)},pagination:e.getList}}),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible,"close-on-click-modal":!1},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"editForm",attrs:{model:e.editForm,rules:e.editFormRules,"label-width":"130px"}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"阅读器名称:",prop:"readerName"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"阅读器名称"},model:{value:e.editForm.readerName,callback:function(t){e.$set(e.editForm,"readerName",t)},expression:"editForm.readerName"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"阅读器IP地址:",prop:"readerIP"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"阅读器IP地址"},model:{value:e.editForm.readerIP,callback:function(t){e.$set(e.editForm,"readerIP",t)},expression:"editForm.readerIP"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"是否连接报警器:",prop:"hasAlarm"}},[a("el-radio-group",{model:{value:e.editForm.hasAlarm,callback:function(t){e.$set(e.editForm,"hasAlarm",t)},expression:"editForm.hasAlarm"}},[a("el-radio",{attrs:{label:0}},[e._v("否")]),e._v(" "),a("el-radio",{attrs:{label:1}},[e._v("是")])],1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"接口分类:",prop:"interfaceType"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:e.editForm.interfaceType,callback:function(t){e.$set(e.editForm,"interfaceType",t)},expression:"editForm.interfaceType"}},["create"==e.dialogStatus?a("el-option",{attrs:{value:0,label:"有源（M5)"}}):e._e(),e._v(" "),"create"==e.dialogStatus?a("el-option",{attrs:{value:1,label:"有源（自有)"}}):e._e(),e._v(" "),a("el-option",{attrs:{value:2,label:"无源（1天线)"}}),e._v(" "),a("el-option",{attrs:{value:3,label:"无源（2天线)"}}),e._v(" "),a("el-option",{attrs:{value:4,label:"无源（3天线)"}}),e._v(" "),a("el-option",{attrs:{value:5,label:"无源（4天线）"}})],1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"断线是否报警:",prop:"isAlarm"}},[a("el-radio-group",{model:{value:e.editForm.isAlarm,callback:function(t){e.$set(e.editForm,"isAlarm",t)},expression:"editForm.isAlarm"}},[a("el-radio",{attrs:{label:0}},[e._v("否")]),e._v(" "),a("el-radio",{attrs:{label:1}},[e._v("是")])],1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"品牌:",prop:"brand"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"品牌"},model:{value:e.editForm.brand,callback:function(t){e.$set(e.editForm,"brand",t)},expression:"editForm.brand"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"安装日期:",prop:"setupDate"}},[a("el-date-picker",{staticClass:"filter-item",attrs:{clearable:"",type:"date","value-format":"yyyy-MM-dd",placeholder:"选择安装日期"},model:{value:e.editForm.setupDate,callback:function(t){e.$set(e.editForm,"setupDate",t)},expression:"editForm.setupDate"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"设备型号:",prop:"model"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"设备型号"},model:{value:e.editForm.model,callback:function(t){e.$set(e.editForm,"model",t)},expression:"editForm.model"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"所在位置:",prop:"address\t"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"所在位置",type:"textarea",autosize:{minRows:1,maxRows:4}},model:{value:e.editForm.address,callback:function(t){e.$set(e.editForm,"address",t)},expression:"editForm.address"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"备注:",prop:"remark\t"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"备注",type:"textarea",autosize:{minRows:1,maxRows:4}},model:{value:e.editForm.remark,callback:function(t){e.$set(e.editForm,"remark",t)},expression:"editForm.remark"}})],1)],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.createData}},[e._v("\n        "+e._s("create"==e.dialogStatus?"添加":"保存")+"\n      ")])],1)],1)],1)},i=[],l=a("365c"),o=a("333d"),s={name:"index.vue",components:{Pagination:o["a"]},data:function(){return{loading:!0,listQuery:{pageNum:1,pageSize:20,lastStatus:"",readerName:"",address:"",readerIP:""},total:0,list:[],dialogStatus:"",textMap:{update:"编辑",create:"新增"},dialogFormVisible:!1,editForm:{hasAlarm:"",interfaceType:"",isAlarm:"",readerIP:"",readerName:"",setupDate:"",address:"",brand:"",model:"",remark:""},editFormRules:{hasAlarm:[{required:!0,message:"请选择是否连接报警器",trigger:"blur"}],interfaceType:[{required:!0,message:"请选择接口分类",trigger:"blur"}],isAlarm:[{required:!0,message:"请选择断线是否报警",trigger:"blur"}],readerIP:[{required:!0,message:"阅读器IP地址不能为空",trigger:"blur"}],readerName:[{required:!0,message:"阅读器名称不能为空",trigger:"blur"}],setupDate:[{required:!0,message:"请选择安装日期",trigger:"blur"}]}}},created:function(){this.getList()},methods:{getList:function(){var e=this;this.loading=!0,Object(l["c"])("rfidreader/page",this.listQuery).then((function(t){e.loading=!1,e.list=t.data.list,e.total=t.data.total})).catch((function(t){e.loading=!1,e.list=[],e.total=0}))},handleAdd:function(){var e=this;this.dialogStatus="create",this.dialogFormVisible=!0,this.editForm={hasAlarm:0,interfaceType:"",isAlarm:0,readerIP:"",readerName:"",setupDate:"",address:"",brand:"",model:"",remark:""},this.$nextTick((function(){e.$refs["editForm"].clearValidate()}))},handleEdit:function(e,t){var a=this;if(t)for(var r in t)null!=t[r]&&"undefined"!==typeof t[r]&&void 0!=t[r]||(t[r]="");this.dialogStatus="update",this.dialogFormVisible=!0,this.editForm=Object.assign({},t),this.$nextTick((function(){a.$refs["editForm"].clearValidate()}))},createData:function(){var e=this;this.$refs["editForm"].validate((function(t){t&&Object(l["c"])("rfidreader/".concat("create"==e.dialogStatus?"add":"update"),e.editForm).then((function(t){200==t.code?(e.dialogFormVisible=!1,e.getList(),e.$message.success({message:"create"==e.dialogStatus?"提交成功":"保存成功",type:"success"})):e.$message.error(t.message)})).catch((function(t){e.$message.error(t.message)}))}))},handleDel:function(e,t){var a=this;Object(l["c"])("rfidreader/delete/"+t.id).then((function(r){a.$message.success({type:"success",message:"删除成功"}),a.list.splice(e,1),a.total--,a.$refs["popover-"+t.id].doClose()})).catch((function(e){a.$refs["popover-"+t.id].doClose(),a.$message.error(e.message)}))}}},n=s,c=a("2877"),u=Object(c["a"])(n,r,i,!1,null,"a0f0098a",null);t["default"]=u.exports},fdef:function(e,t){e.exports="\t\n\v\f\r   ᠎             　\u2028\u2029\ufeff"}}]);
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0dac414c"],{"11e9":function(t,e,a){var s=a("52a7"),r=a("4630"),o=a("6821"),i=a("6a99"),l=a("69a8"),n=a("c69a"),c=Object.getOwnPropertyDescriptor;e.f=a("9e1e")?c:function(t,e){if(t=o(t),e=i(e,!0),n)try{return c(t,e)}catch(a){}if(l(t,e))return r(!s.f.call(t,e),t[e])}},"333d":function(t,e,a){"use strict";var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[a("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,"page-sizes":t.pageSizes,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:current-page":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"update:page-size":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},r=[];a("c5f6");Math.easeInOutQuad=function(t,e,a,s){return t/=s/2,t<1?a/2*t*t+e:(t--,-a/2*(t*(t-2)-1)+e)};var o=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)}}();function i(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}function l(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function n(t,e,a){var s=l(),r=t-s,n=20,c=0;e="undefined"===typeof e?500:e;var u=function t(){c+=n;var l=Math.easeInOutQuad(c,s,r,e);i(l),c<e?o(t):a&&"function"===typeof a&&a()};u()}var c={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&n(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&n(0,800)}}},u=c,d=(a("e498"),a("2877")),p=Object(d["a"])(u,s,r,!1,null,"6af373ef",null);e["a"]=p.exports},"5dbc":function(t,e,a){var s=a("d3f4"),r=a("8b97").set;t.exports=function(t,e,a){var o,i=e.constructor;return i!==a&&"function"==typeof i&&(o=i.prototype)!==a.prototype&&s(o)&&r&&r(t,o),t}},7456:function(t,e,a){},"8b97":function(t,e,a){var s=a("d3f4"),r=a("cb7c"),o=function(t,e){if(r(t),!s(e)&&null!==e)throw TypeError(e+": can't set as prototype!")};t.exports={set:Object.setPrototypeOf||("__proto__"in{}?function(t,e,s){try{s=a("9b43")(Function.call,a("11e9").f(Object.prototype,"__proto__").set,2),s(t,[]),e=!(t instanceof Array)}catch(r){e=!0}return function(t,a){return o(t,a),e?t.__proto__=a:s(t,a),t}}({},!1):void 0),check:o}},9093:function(t,e,a){var s=a("ce10"),r=a("e11e").concat("length","prototype");e.f=Object.getOwnPropertyNames||function(t){return s(t,r)}},aa77:function(t,e,a){var s=a("5ca1"),r=a("be13"),o=a("79e5"),i=a("fdef"),l="["+i+"]",n="​",c=RegExp("^"+l+l+"*"),u=RegExp(l+l+"*$"),d=function(t,e,a){var r={},l=o((function(){return!!i[t]()||n[t]()!=n})),c=r[t]=l?e(p):i[t];a&&(r[a]=c),s(s.P+s.F*l,"String",r)},p=d.trim=function(t,e){return t=String(r(t)),1&e&&(t=t.replace(c,"")),2&e&&(t=t.replace(u,"")),t};t.exports=d},aa82:function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-card",{staticClass:"operate-container",attrs:{shadow:"never"}},[a("el-form",{attrs:{inline:!0,model:t.listQuery},nativeOn:{submit:function(t){t.preventDefault()}}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"部门名称:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:t.listQuery.orgID,callback:function(e){t.$set(t.listQuery,"orgID",e)},expression:"listQuery.orgID"}},t._l(t.orgOptions,(function(t){return a("el-option",{key:t.orgID,attrs:{label:t.orgName,value:t.orgID}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"资产名称:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:t.listQuery.assetID,callback:function(e){t.$set(t.listQuery,"assetID",e)},expression:"listQuery.assetID"}},t._l(t.assetOptions,(function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"出库类型:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:t.listQuery.outType,callback:function(e){t.$set(t.listQuery,"outType",e)},expression:"listQuery.outType"}},[a("el-option",{attrs:{value:"0",label:"使用出库"}}),t._v(" "),a("el-option",{attrs:{value:"1",label:"借用出库"}})],1)],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"当前状态:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:t.listQuery.status,callback:function(e){t.$set(t.listQuery,"status",e)},expression:"listQuery.status"}},[a("el-option",{attrs:{value:"0",label:"未出库"}}),t._v(" "),a("el-option",{attrs:{value:"1",label:"已出库"}}),t._v(" "),a("el-option",{attrs:{value:"2",label:"已归还"}})],1)],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"success",size:"mini",icon:"el-icon-search"},on:{click:t.getList}},[t._v("查询")])],1)],1)],1),t._v(" "),a("div",{staticClass:"btn-btm"},[a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:t.handleAdd}},[t._v("添加")])],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],ref:"table",staticStyle:{width:"100%"},attrs:{data:t.list,border:"","header-cell-style":{background:"#F8F8F9",fontSize:"13px",color:"#515a6e"}}},[a("af-table-column",{attrs:{prop:"address",fixed:"left",label:"出库目的地"}}),t._v(" "),a("af-table-column",{attrs:{prop:"outType",label:"出库类型"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s({0:"使用出库",1:"借用出库"}[e.row.outType])+"\n      ")]}}])}),t._v(" "),a("af-table-column",{attrs:{prop:"lendStart",label:"出库开始时间"}}),t._v(" "),a("af-table-column",{attrs:{prop:"status",label:"当前状态"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s({0:"未出库",1:"已出库",2:"已归还"}[e.row.status])+"\n      ")]}}])}),t._v(" "),a("af-table-column",{attrs:{prop:"outTime",label:"实际出库检测到时间"}}),t._v(" "),a("af-table-column",{attrs:{prop:"returnTime",label:"归还时间"}}),t._v(" "),a("af-table-column",{attrs:{prop:"estimateTime",label:"预计归还时间"}}),t._v(" "),a("af-table-column",{attrs:{prop:"inTime",label:"实际入库检测到时间"}}),t._v(" "),a("af-table-column",{attrs:{prop:"assetName",label:"资产名称"}}),t._v(" "),a("af-table-column",{attrs:{prop:"borrowOrg",label:"借用部门"}}),t._v(" "),a("af-table-column",{attrs:{prop:"borrowMan",label:"用途"}}),t._v(" "),a("af-table-column",{attrs:{prop:"lendEnd",label:"最迟结束时间"}}),t._v(" "),a("af-table-column",{attrs:{prop:"regTime",label:"登记时间"}}),t._v(" "),a("af-table-column",{attrs:{prop:"remark",label:"备注"}}),t._v(" "),a("af-table-column",{attrs:{prop:"usages",label:"用途"}}),t._v(" "),a("el-table-column",{attrs:{fixed:"right",label:"操作","min-width":"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{staticClass:"btn",attrs:{type:"text",size:"small"},on:{click:function(a){return t.handleEdit(e.$index,e.row)}}},[t._v("修改")]),t._v(" "),a("el-popover",{ref:"popover-"+e.row.id,attrs:{placement:"top",width:"200",trigger:"manual"}},[a("p",[t._v("确定删除该数据吗？")]),t._v(" "),a("div",{staticStyle:{"text-align":"right",margin:"0"}},[a("el-button",{attrs:{size:"mini",type:"text"},on:{click:function(a){t.$refs["popover-"+e.row.id].doClose()}}},[t._v("取消")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){return t.handleDel(e.$index,e.row)}}},[t._v("确定")])],1),t._v(" "),""==e.row.status&&0!=e.row.status?a("el-button",{staticClass:"btn",attrs:{slot:"reference",type:"text",size:"small"},on:{click:function(a){t.$refs["popover-"+e.row.id].doShow()}},slot:"reference"},[t._v("删除")]):t._e()],1)]}}])})],1),t._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total,page:t.listQuery.pageNum,limit:t.listQuery.pageSize},on:{"update:page":function(e){return t.$set(t.listQuery,"pageNum",e)},"update:limit":function(e){return t.$set(t.listQuery,"pageSize",e)},pagination:t.getList}}),t._v(" "),a("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogFormVisible,"close-on-click-modal":!1,width:"55%"},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"editForm",attrs:{model:t.editForm,rules:t.editFormRules,"label-width":"130px"}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"借用部门:",prop:"borrowOrg"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1,"popper-class":"select-popper"},on:{change:t.orgChange},model:{value:t.editForm.borrowOrg,callback:function(e){t.$set(t.editForm,"borrowOrg",e)},expression:"editForm.borrowOrg"}},t._l(t.orgOptions,(function(t){return a("el-option",{key:t.orgID,attrs:{label:t.orgName,value:t.orgID}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"借用人:",prop:"borrowMan"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"借用人"},model:{value:t.editForm.borrowMan,callback:function(e){t.$set(t.editForm,"borrowMan",e)},expression:"editForm.borrowMan"}})],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"出库目的地:",prop:"address"}},[a("el-input",{staticClass:"filter-item",attrs:{disabled:"create"!=t.dialogStatus,type:"textarea",autosize:"",placeholder:"出库目的地"},model:{value:t.editForm.address,callback:function(e){t.$set(t.editForm,"address",e)},expression:"editForm.address"}})],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"选择资产:",prop:"assetID"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"",filterable:"","popper-append-to-body":!1,disabled:"create"!=t.dialogStatus,"popper-class":"select-popper"},model:{value:t.editForm.assetID,callback:function(e){t.$set(t.editForm,"assetID",e)},expression:"editForm.assetID"}},t._l(t.assetOptions,(function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"出库类型:",prop:"outType"}},[a("el-radio-group",{attrs:{disabled:"create"!=t.dialogStatus},model:{value:t.editForm.outType,callback:function(e){t.$set(t.editForm,"outType",e)},expression:"editForm.outType"}},[a("el-radio",{attrs:{label:0}},[t._v("使用出库")]),t._v(" "),a("el-radio",{attrs:{label:1}},[t._v("借用出库")])],1)],1)],1),t._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"最迟归还时间:",prop:"lendEnd"}},[a("el-date-picker",{staticClass:"filter-item",attrs:{type:"datetime",disabled:"create"!=t.dialogStatus,"value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择最迟归还时间"},model:{value:t.editForm.lendEnd,callback:function(e){t.$set(t.editForm,"lendEnd",e)},expression:"editForm.lendEnd"}})],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"出库开始时间:",prop:"lendStart"}},[a("el-date-picker",{staticClass:"filter-item",attrs:{type:"datetime",disabled:"create"!=t.dialogStatus,"value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择出库开始时间"},model:{value:t.editForm.lendStart,callback:function(e){t.$set(t.editForm,"lendStart",e)},expression:"editForm.lendStart"}})],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"预计归还时间:",prop:"estimateTime"}},[a("el-date-picker",{staticClass:"filter-item",attrs:{type:"datetime","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择预计归还时间"},model:{value:t.editForm.estimateTime,callback:function(e){t.$set(t.editForm,"estimateTime",e)},expression:"editForm.estimateTime"}})],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"备注:",prop:"remark\t"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"备注",type:"textarea",autosize:{minRows:1,maxRows:4}},model:{value:t.editForm.remark,callback:function(e){t.$set(t.editForm,"remark",e)},expression:"editForm.remark"}})],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"用途:",prop:"usages\t"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"用途",type:"textarea",autosize:{minRows:1,maxRows:4}},model:{value:t.editForm.usages,callback:function(e){t.$set(t.editForm,"usages",e)},expression:"editForm.usages"}})],1)],1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取消")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:t.createData}},[t._v("\n        "+t._s("create"==t.dialogStatus?"添加":"保存")+"\n      ")])],1)],1),t._v(" "),a("el-dialog",{attrs:{title:"修改状态",visible:t.statusFormVisible,width:"30%"},on:{"update:visible":function(e){t.statusFormVisible=e}}},[a("el-form",{ref:"statusForm",attrs:{model:t.statusForm,rules:t.statusFormRules,"label-width":"100px"}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"选择状态:",prop:"status"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:t.statusForm.status,callback:function(e){t.$set(t.statusForm,"status",e)},expression:"statusForm.status"}},[a("el-option",{attrs:{value:0,label:"还未出库"}}),t._v(" "),a("el-option",{attrs:{value:1,label:"已出库"}}),t._v(" "),a("el-option",{attrs:{value:2,label:"已归还"}})],1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(e){t.statusFormVisible=!1}}},[t._v("取消")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:t.createStatus}},[t._v("提交")])],1)],1),t._v(" "),0==t.fistFlag?a("amend-pwd"):t._e()],1)},r=[],o=a("5530"),i=a("365c"),l=a("333d"),n=a("2f62"),c={name:"outbound",components:{Pagination:l["a"]},computed:Object(o["a"])({},Object(n["b"])(["name","fistFlag","userForm"])),data:function(){return{listQuery:{pageSize:20,pageNum:1,orgID:"",outType:"",status:"",assetID:""},loading:!0,total:0,list:[],orgOptions:[],assetOptions:[],dialogStatus:"",textMap:{update:"编辑",create:"新增"},dialogFormVisible:!1,editForm:{address:"",assetID:"",lendEnd:"",lendStart:"",outType:"",borrowMan:"",borrowOrg:"",estimateTime:"",remark:"",usages:""},editFormRules:{address:[{required:!0,message:"出库目的地不能为空",trigger:"blur"}],assetID:[{required:!0,message:"请选择资产",trigger:"blur"}],lendEnd:[{required:!0,message:"请选择最迟归还时间",trigger:"blur"}],lendStart:[{required:!0,message:"请选择出库开始时间",trigger:"blur"}],outType:[{required:!0,message:"请选择出库类型",trigger:"blur"}]},statusFormVisible:!1,statusForm:{status:"",id:""},statusFormRules:{status:[{required:!0,message:"请选择状态",trigger:"blur"}]}}},created:function(){this.getList(),this.orgGetList(),this.assetGetList()},methods:{getList:function(){var t=this;this.loading=!0,Object(i["c"])("assetoutreg/page",this.listQuery).then((function(e){t.loading=!1,t.list=e.data.list,t.total=e.data.total})).catch((function(e){t.loading=!1,t.list=[],t.total=0}))},handleAdd:function(){var t=this;this.dialogFormVisible=!0,this.dialogStatus="create",this.editForm={address:"",assetID:"",lendEnd:"",lendStart:"",outType:0,borrowMan:"",borrowOrg:"",estimateTime:"",remark:"",usages:""},this.$nextTick((function(){t.$refs["editForm"].clearValidate()}))},handleEdit:function(t,e){var a=this;this.dialogFormVisible=!0,this.dialogStatus="update",this.editForm=Object.assign({},e),this.$nextTick((function(){a.$refs["editForm"].clearValidate()}))},createStatus:function(){var t=this;this.$refs["statusForm"].validate((function(e){e&&Object(i["c"])("assetoutreg/chgStatus",t.statusForm).then((function(e){200==e.code?(t.statusFormVisible=!1,t.getList(),t.$message.success({message:"提交成功",type:"success"})):t.$message.error(e.message)})).catch((function(e){t.$message.error(e.message)}))}))},createData:function(){var t=this;this.$refs["editForm"].validate((function(e){e&&Object(i["c"])("assetoutreg/".concat("create"==t.dialogStatus?"add":"update"),t.editForm).then((function(e){200==e.code?(t.dialogFormVisible=!1,t.getList(),t.$message.success({message:"create"==t.dialogStatus?"提交成功":"保存成功",type:"success"})):t.$message.error(e.message)})).catch((function(e){t.$message.error(e.message)}))}))},handleDel:function(t,e){var a=this;Object(i["c"])("assetoutreg/delete/"+e.id).then((function(s){a.$message.success({type:"success",message:"删除成功"}),a.list.splice(t,1),a.total--,a.$refs["popover-"+e.id].doClose()})).catch((function(t){a.$refs["popover-"+e.id].doClose(),a.$message.error(t.message)}))},orgGetList:function(){var t=this;Object(i["c"])("user/getOrg").then((function(e){t.orgOptions=e.data})).catch((function(e){t.orgOptions=[]}))},assetGetList:function(){var t=this;Object(i["c"])("asset/list").then((function(e){t.assetOptions=e.data})).catch((function(e){t.assetOptions=[]}))}}},u=c,d=a("2877"),p=Object(d["a"])(u,s,r,!1,null,"7798f1bf",null);e["default"]=p.exports},c5f6:function(t,e,a){"use strict";var s=a("7726"),r=a("69a8"),o=a("2d95"),i=a("5dbc"),l=a("6a99"),n=a("79e5"),c=a("9093").f,u=a("11e9").f,d=a("86cc").f,p=a("aa77").trim,m="Number",f=s[m],b=f,g=f.prototype,v=o(a("2aeb")(g))==m,h="trim"in String.prototype,y=function(t){var e=l(t,!1);if("string"==typeof e&&e.length>2){e=h?e.trim():p(e,3);var a,s,r,o=e.charCodeAt(0);if(43===o||45===o){if(a=e.charCodeAt(2),88===a||120===a)return NaN}else if(48===o){switch(e.charCodeAt(1)){case 66:case 98:s=2,r=49;break;case 79:case 111:s=8,r=55;break;default:return+e}for(var i,n=e.slice(2),c=0,u=n.length;c<u;c++)if(i=n.charCodeAt(c),i<48||i>r)return NaN;return parseInt(n,s)}}return+e};if(!f(" 0o1")||!f("0b1")||f("+0x1")){f=function(t){var e=arguments.length<1?0:t,a=this;return a instanceof f&&(v?n((function(){g.valueOf.call(a)})):o(a)!=m)?i(new b(y(e)),a,f):y(e)};for(var _,F=a("9e1e")?c(b):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger".split(","),w=0;F.length>w;w++)r(b,_=F[w])&&!r(f,_)&&d(f,_,u(b,_));f.prototype=g,g.constructor=f,a("2aba")(s,m,f)}},e498:function(t,e,a){"use strict";a("7456")},fdef:function(t,e){t.exports="\t\n\v\f\r   ᠎             　\u2028\u2029\ufeff"}}]);
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4e1c747e"],{"11e9":function(t,e,a){var n=a("52a7"),i=a("4630"),r=a("6821"),o=a("6a99"),s=a("69a8"),l=a("c69a"),c=Object.getOwnPropertyDescriptor;e.f=a("9e1e")?c:function(t,e){if(t=r(t),e=o(e,!0),l)try{return c(t,e)}catch(a){}if(s(t,e))return i(!n.f.call(t,e),t[e])}},"333d":function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[a("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,"page-sizes":t.pageSizes,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:current-page":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"update:page-size":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},i=[];a("c5f6");Math.easeInOutQuad=function(t,e,a,n){return t/=n/2,t<1?a/2*t*t+e:(t--,-a/2*(t*(t-2)-1)+e)};var r=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)}}();function o(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}function s(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function l(t,e,a){var n=s(),i=t-n,l=20,c=0;e="undefined"===typeof e?500:e;var u=function t(){c+=l;var s=Math.easeInOutQuad(c,n,i,e);o(s),c<e?r(t):a&&"function"===typeof a&&a()};u()}var c={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&l(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&l(0,800)}}},u=c,p=(a("e498"),a("2877")),f=Object(p["a"])(u,n,i,!1,null,"6af373ef",null);e["a"]=f.exports},"5dbc":function(t,e,a){var n=a("d3f4"),i=a("8b97").set;t.exports=function(t,e,a){var r,o=e.constructor;return o!==a&&"function"==typeof o&&(r=o.prototype)!==a.prototype&&n(r)&&i&&i(t,r),t}},7456:function(t,e,a){},"8b97":function(t,e,a){var n=a("d3f4"),i=a("cb7c"),r=function(t,e){if(i(t),!n(e)&&null!==e)throw TypeError(e+": can't set as prototype!")};t.exports={set:Object.setPrototypeOf||("__proto__"in{}?function(t,e,n){try{n=a("9b43")(Function.call,a("11e9").f(Object.prototype,"__proto__").set,2),n(t,[]),e=!(t instanceof Array)}catch(i){e=!0}return function(t,a){return r(t,a),e?t.__proto__=a:n(t,a),t}}({},!1):void 0),check:r}},9093:function(t,e,a){var n=a("ce10"),i=a("e11e").concat("length","prototype");e.f=Object.getOwnPropertyNames||function(t){return n(t,i)}},aa77:function(t,e,a){var n=a("5ca1"),i=a("be13"),r=a("79e5"),o=a("fdef"),s="["+o+"]",l="​",c=RegExp("^"+s+s+"*"),u=RegExp(s+s+"*$"),p=function(t,e,a){var i={},s=r((function(){return!!o[t]()||l[t]()!=l})),c=i[t]=s?e(f):o[t];a&&(i[a]=c),n(n.P+n.F*s,"String",i)},f=p.trim=function(t,e){return t=String(i(t)),1&e&&(t=t.replace(c,"")),2&e&&(t=t.replace(u,"")),t};t.exports=p},c13c:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-card",{staticClass:"operate-container",attrs:{shadow:"never"}},[a("el-form",{attrs:{inline:!0,model:t.listQuery},nativeOn:{submit:function(t){t.preventDefault()}}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"盘点结果类型:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:t.listQuery.checkResult,callback:function(e){t.$set(t.listQuery,"checkResult",e)},expression:"listQuery.checkResult"}},[a("el-option",{attrs:{value:0,label:"吻合"}}),t._v(" "),a("el-option",{attrs:{value:1,label:"此资产应该在另一个库房"}}),t._v(" "),a("el-option",{attrs:{value:2,label:"此资产应该在外面"}}),t._v(" "),a("el-option",{attrs:{value:3,label:"未盘点"}})],1)],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"盘点结果确认:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:t.listQuery.resultCheck,callback:function(e){t.$set(t.listQuery,"resultCheck",e)},expression:"listQuery.resultCheck"}},[a("el-option",{attrs:{value:0,label:"未确认"}}),t._v(" "),a("el-option",{attrs:{value:1,label:"已确认"}})],1)],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"success",size:"mini",icon:"el-icon-search"},on:{click:t.getList}},[t._v("查询")])],1)],1)],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],ref:"table",staticStyle:{width:"100%"},attrs:{data:t.list,border:"","header-cell-style":{background:"#F8F8F9",fontSize:"13px",color:"#515a6e"}}},[a("af-table-column",{attrs:{prop:"orgName",fixed:"left",label:"所属部门名称"}}),t._v(" "),a("af-table-column",{attrs:{prop:"checkResult",label:"盘点结果类型",width:"200"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s({0:"吻合",1:"此资产应该在另一个库房",2:"此资产应该在外面",3:"未盘点"}[e.row.checkResult])+"\n      ")]}}])}),t._v(" "),a("af-table-column",{attrs:{prop:"assetName",label:"资产名称"}}),t._v(" "),a("af-table-column",{attrs:{prop:"roomName",label:"楼宇名称"}}),t._v(" "),a("af-table-column",{attrs:{prop:"shouldRoomName",label:"应该在的楼宇名称"}}),t._v(" "),a("af-table-column",{attrs:{prop:"resultCheck",label:"盘点结果确认",width:"140"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s({0:"未确认",1:"已确认"}[e.row.resultCheck])+"\n      ")]}}])}),t._v(" "),a("af-table-column",{attrs:{prop:"checkTime",label:"盘点时间"}})],1),t._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total,page:t.listQuery.pageNum,limit:t.listQuery.pageSize},on:{"update:page":function(e){return t.$set(t.listQuery,"pageNum",e)},"update:limit":function(e){return t.$set(t.listQuery,"pageSize",e)},pagination:t.getList}}),t._v(" "),a("el-dialog",{attrs:{title:"盘点结果损益确认",visible:t.statusFormVisible,width:"30%"},on:{"update:visible":function(e){t.statusFormVisible=e}}},[a("el-form",{ref:"confirmForm",attrs:{model:t.confirmForm,rules:t.statusFormRules,"label-width":"120px"}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"选择需要确认资产:",prop:"assetIDs"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"",multiple:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:t.confirmForm.assetIDs,callback:function(e){t.$set(t.confirmForm,"assetIDs",e)},expression:"confirmForm.assetIDs"}},t._l(t.assetOptions,(function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})})),1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(e){t.statusFormVisible=!1}}},[t._v("取消")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:t.createStatus}},[t._v("提交")])],1)],1),t._v(" "),0==t.fistFlag?a("amend-pwd"):t._e()],1)},i=[],r=a("5530"),o=a("365c"),s=a("333d"),l=a("2f62"),c={name:"listing",components:{Pagination:s["a"]},computed:Object(r["a"])({},Object(l["b"])(["name","fistFlag","userForm"])),data:function(){return{listQuery:{taskID:"",checkResult:"",resultCheck:"",type:0,pageNum:1,pageSize:20},list:[],loading:!0,total:0,taskOptions:[],confirmForm:{id:"",assetIDs:""},statusFormVisible:!1,statusFormRules:{assetIDs:[{required:!0,message:"请选择需要确认资产",trigger:"blur"}]},assetOptions:[]}},created:function(){this.getList(),this.assetGetList()},methods:{getList:function(){var t=this;if(this.loading=!0,null!=this.$route.params.id){var e=this.$route.params.id;this.listQuery.taskID=e,Object(o["c"])("invetoryasset/page",this.listQuery).then((function(e){t.loading=!1,t.list=e.data.list,t.total=e.data.total})).catch((function(e){t.loading=!1,t.list=[],t.total=0}))}},taskGetList:function(){var t=this;Object(o["c"])("invetorytask/page").then((function(e){t.taskOptions=e.data.list})).catch((function(e){t.taskOptions=[]}))},assetGetList:function(){var t=this;Object(o["c"])("asset/page").then((function(e){t.assetOptions=e.data.list})).catch((function(e){t.assetOptions=[]}))},handleEdit:function(t,e){var a=this;this.statusFormVisible=!0,this.confirmForm={id:e.taskID,assetIDs:""},this.$nextTick((function(){a.$refs["confirmForm"].clearValidate()}))},createStatus:function(){var t=this;this.$refs["confirmForm"].validate((function(e){e&&Object(o["c"])("invetoryasset/confirm",t.confirmForm).then((function(e){200==e.code?(t.statusFormVisible=!1,t.getList(),t.$message.success({message:"提交成功",type:"success"})):t.$message.error(e.message)})).catch((function(e){t.$message.error(e.message)}))}))}}},u=c,p=a("2877"),f=Object(p["a"])(u,n,i,!1,null,"dccfd7d2",null);e["default"]=f.exports},c5f6:function(t,e,a){"use strict";var n=a("7726"),i=a("69a8"),r=a("2d95"),o=a("5dbc"),s=a("6a99"),l=a("79e5"),c=a("9093").f,u=a("11e9").f,p=a("86cc").f,f=a("aa77").trim,d="Number",m=n[d],g=m,h=m.prototype,b=r(a("2aeb")(h))==d,v="trim"in String.prototype,y=function(t){var e=s(t,!1);if("string"==typeof e&&e.length>2){e=v?e.trim():f(e,3);var a,n,i,r=e.charCodeAt(0);if(43===r||45===r){if(a=e.charCodeAt(2),88===a||120===a)return NaN}else if(48===r){switch(e.charCodeAt(1)){case 66:case 98:n=2,i=49;break;case 79:case 111:n=8,i=55;break;default:return+e}for(var o,l=e.slice(2),c=0,u=l.length;c<u;c++)if(o=l.charCodeAt(c),o<48||o>i)return NaN;return parseInt(l,n)}}return+e};if(!m(" 0o1")||!m("0b1")||m("+0x1")){m=function(t){var e=arguments.length<1?0:t,a=this;return a instanceof m&&(b?l((function(){h.valueOf.call(a)})):r(a)!=d)?o(new g(y(e)),a,m):y(e)};for(var _,k=a("9e1e")?c(g):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger".split(","),w=0;k.length>w;w++)i(g,_=k[w])&&!i(m,_)&&p(m,_,u(g,_));m.prototype=h,h.constructor=m,a("2aba")(n,d,m)}},e498:function(t,e,a){"use strict";a("7456")},fdef:function(t,e){t.exports="\t\n\v\f\r   ᠎             　\u2028\u2029\ufeff"}}]);
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d222cce"],{cfc8:function(t,e,a){"use strict";a.r(e);var l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],ref:"table",staticStyle:{width:"100%"},attrs:{data:t.list,border:"","header-cell-style":{background:"#F8F8F9",fontSize:"13px",color:"#515a6e"}}},[a("af-table-column",{attrs:{prop:"orgName",fixed:"left",label:"部门名称"}}),t._v(" "),a("af-table-column",{attrs:{prop:"resultCheck",label:"盘点结果确认"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s({0:"未确认",1:"已确认"}[e.row.resultCheck])+"\n      ")]}}])}),t._v(" "),a("af-table-column",{attrs:{prop:"assetName",label:"资产名称"}}),t._v(" "),a("af-table-column",{attrs:{prop:"assetNo",label:"库房名称"}}),t._v(" "),a("af-table-column",{attrs:{prop:"orgName",label:"资产编号"}})],1),t._v(" "),0==t.fistFlag?a("amend-pwd"):t._e()],1)},n=[],s=a("5530"),o=a("365c"),r=a("2f62"),i={name:"results",computed:Object(s["a"])({},Object(r["b"])(["name","fistFlag","userForm"])),data:function(){return{list:[],loading:!0}},created:function(){this.getList()},methods:{getList:function(){var t=this;if(this.loading=!0,null!=this.$route.params.id){var e=this.$route.params.id;Object(o["c"])("invetorylack/getResultByTaskId/"+e).then((function(e){t.loading=!1,t.list=e.data.list})).catch((function(e){t.loading=!1,t.list=[]}))}}}},c=i,u=a("2877"),d=Object(u["a"])(c,l,n,!1,null,"b6e189e6",null);e["default"]=d.exports}}]);
<template>
  <div class="overflow-auto">
    <b-card bg-variant="light" header="상품 목록">
    <b-table
      id="my-table"
      :fields="fields"
      responsive :items="fetchData"
      :per-page="perPage"
      :current-page="currentPage"
      small
      outlined="outlined"
      caption-top=""
      :busy="isBusy"
    >
     <template v-slot:table-busy>
        <div class="text-center text-danger my-2">
          <b-spinner class="align-middle"></b-spinner>
          <strong>Loading...</strong>
        </div>
      </template>
     <template v-slot:cell(actions)="row">
        <b-button size="sm" @click="deleteGoodsModal(row.item, row.index, $event.target)" class="mr-1">
          삭제
        </b-button>
        <b-button size="sm" @click="row.toggleDetails">
        <!-- <b-button v-b-modal.modal-prevent-closing>     -->
          {{ row.detailsShowing ? 'Hide' : 'Show' }} Details
        </b-button>
        <!-- <b-button size="sm" v-b-modal.modal-prevent-closing> -->
        <b-button size="sm" @click="updateGoodsModal(row.item, row.index, $event.target)">
            update
        </b-button>
    </template>
    <template v-slot:row-details="row">
        <b-card>
          <ul>
            <li v-for="(value, key) in row.item" :key="key">{{ key }}: {{ value }}</li>
          </ul>
        </b-card>
    </template>
    </b-table>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      align="center"
    ></b-pagination>

     <!-- Info modal -->
    <b-modal :id="infoModal.id" :title="infoModal.title" ok-only @hide="deleteGoods">
      <pre>{{ infoModal.content }}</pre>
    </b-modal>

    <b-modal
      id="modal-prevent-closing"
      ref="modal"
      title="Update Goods Info"
      @show="resetModal"
      @hidden="resetModal"
      @ok="updateGoods"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group
          :state="goodsNameState"
          label="상품 이름"
          label-for="goodsName-input"
          invalid-feedback="Goods Name is required"
        >
          <b-form-input
            id="goodsName-input"
            v-model="updateModal.updateGoodsName"
            :state="goodsNameState"
            required
          ></b-form-input>
        </b-form-group>
         <b-form-group
          :state="goodsCodeState"
          label="상품 코드"
          label-for="goodsCode-input"
        >
          <b-form-input
            id="goodsCode-input"
            v-model="updateModal.updateGoodsCode"
            :state="goodsCodeState"
            readonly=""
          ></b-form-input>
        </b-form-group>
         <b-form-group
          :state="goodsPriceState"
          label="상품 가격"
          label-for="goodsPrice-input"
          invalid-feedback="GoodsPrice is required"
        >
          <b-form-input
            id="goodsPrice-input"
            v-model="updateModal.updateGoodsPrice"
            :state="goodsPriceState"
            required
          ></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
    </b-card>
  </div>
</template>
<script>
/* eslint-disable*/
import {goodsAllList, goodsDelete, goodsUpdate} from '../api'
  export default {
    data() {
      return {
        isBusy: false,
        perPage: 5,
        currentPage: 1,
        goodsList:[],
        fields: [
          { key: 'goodsName', label: '상품 이름', sortable: true, class: 'text-center', sortDirection: 'desc' },
          { key: 'goodsCode', label: '상품 코드', sortable: true, class: 'text-center' },
          { key: 'goodsPrice', label: '상품 가격', sortable: true, class: 'text-center' },
          { key: 'actions', label: 'Actions', class: 'text-center' }
        ],
        totalCount: 10,
        infoModal: {
          id: 'info-modal',
          title: '',
          content: '',
          deleteGoodsCode: ''
        },
        updateModal:{
          id:'modal-prevent-closing',
          updateGoodsName: '1',
          updateGoodsCode: '2',
          updateGoodsPrice: '3'
        }
      }
    },
    created(){
            this.fetchData()

    },
    methods: {
        fetchData(ctx,callback) {
            !this.isBusy
            goodsAllList.fetch(this.perPage, (this.currentPage-1))
                .then(data =>{
                    this.goodsList = data.goodsInfoResDTO
                    this.totalCount = data.totalCount
                    callback(this.goodsList)
                })
                .finally(_=>{
                  this.isBusy

                })
        },
        deleteGoodsModal(item, index, button) {
            this.infoModal.title = 'Data Delete'
            this.infoModal.content = JSON.stringify(item, null, 2)
            this.$root.$emit('bv::show::modal', this.infoModal.id, button)
            this.deleteGoodsCode = item.goodsCode
      },
        updateGoodsModal(item, index, button) {
             this.updateModal.updateGoodsName = item.goodsName,
             this.updateModal.updateGoodsCode = item.goodsCode,
             this.updateModal.updateGoodsPrice = item.goodsPrice
          
            this.$root.$emit('bv::show::modal', this.updateModal.id, button)
     
     },
      deleteGoods() {
              !this.isBusy
              goodsDelete.fetch(this.deleteGoodsCode)
                .then(data =>{
                    console.log("success")
                    //this.fetchData(ctx,callback)
                    //location.reload()
                    this.$router.go('/goodsList')
                })
                .finally(_=>{
                    this.isBusy
                    this.deleteGoodsCode = ''        
                    this.infoModal.title = ''
                    this.infoModal.content = ''
                })
      
      },
      updateGoods() {
          !this.isBusy
          goodsUpdate.fetch(this.updateModal,this.updateModal.updateGoodsCode)
                .then(data =>{
                    console.log("upd")
                  
                    this.$route.push({name : "/goodsList"})
                })
                .finally(_=>{
                    this.isBusy
                    this.updateModal.updateGoodsName = "",
                    this.updateModal.updateGoodsCode = "",
                    this.updateModal.updateGoodsPrice = ""
                    console.log('update')
                    

                })
          
 
            },  
    },
    computed: {
      rows() {
      return this.totalCount
      }
    }
     }
</script>
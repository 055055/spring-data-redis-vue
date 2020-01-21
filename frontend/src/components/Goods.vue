<template>
    <div>
        <b-card bg-variant="light" header="상품 등록">
        <div class="text-center" v-if="loading">
             <b-spinner variant="primary" label="Text Centered"></b-spinner>
        </div>
        <div v-else>
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-form-group
            id="input-group-1"
            label="Goods Name :"
            label-for="input-1"
            label-cols-sm="1"
            description="you have to input goods name."
        >
            <b-form-input
            id="input-1"
            v-model="form.goodsName"
            required
            placeholder="Enter Goods Name"
            class="w-25 p-3 mb-1"
            ></b-form-input>
        </b-form-group>
        <b-form-group
        id="input-group-2"
        label="Goods Code:"
        label-for="input-2"
        label-cols-sm="1"
        description="you have to input goods code."
        >
            <b-form-input
            id="input-2"
            v-model="form.goodsCode"
            required
            placeholder="Enter Goods Code"
            class="w-25 p-3 mb-1"
            ></b-form-input>
        </b-form-group>

        <b-form-group
        id="input-group-3"
        label="Price:"
        label-for="input-3"
        label-cols-sm="1"
        description="you have to input goods price."
        >
        <b-form-input
        id="input-3"
        v-model="form.goodsPrice"
        required
        placeholder="Enter Goods Price"
        class="w-25 p-3 mb-1"
        ></b-form-input>
        </b-form-group>

            <b-button type="submit" variant="primary">Submit</b-button>
            <b-button type="reset" variant="danger">Reset</b-button>
        </b-form>
        <b-card class="mt-3" header="Form Data Result">
            <pre class="m-0">{{ msg }}</pre>
        </b-card>
        </div>
        </b-card>
    </div>
</template>
<script>
import {goodsAdd} from '../api'

/* eslint-disable*/
export default {
    data(){
           return {
                loading: false,
                form: {
                goodsName: '',
                goodsCode: '',
                goodsPrice: ''
                },
                show: true,
                msg:''
            }
    },
    methods:{
        addGoods(){
           var goodsName =  this.goodsName.trim();
           var goodsCode =  this.goodsCode.trim();
           console.log(goodsName+goodsCode)
           localStorage.setItem(goodsCode,goodsName)
           this.clearInput();
        },
        clearInput(){
              this.goodsName='';
               this.goodsCode='';
        },
        onSubmit(evt) {
            this.loading =true
            evt.preventDefault()
            goodsAdd.fetch(this.form)
                .then(data =>{
                    this.msg = data
                })
                .finally(_=>{
                    this.loading =false
                })
      },
      onReset(evt) {
        evt.preventDefault()
        // Reset our form values
        this.form.goodsName = ''
        this.form.goodsCode = ''
        this.form.goodsPrice = ''
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }

    }
}
</script>
<style lang="">
    
</style>
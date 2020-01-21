/* eslint-disable*/
//backend api call

import axios from 'axios'
import router from '../router'

const DOMAIN = 'http://localhost:3000'

const request = (method, url, data) => {
    return axios({
            method,
            url: DOMAIN + url,
            data
        }).then(result => result.data)
        .catch(result => {
            const { status } = result.response
            console.log("error")
        })
}

export const goodsAllList = {
    fetch(perPage, currentPage) {
        //return request('get', '/goods?page=' + Number(currentPage) + '&size=' + Number(perPage))
        return request('get', '/goods?page=' + currentPage + '&size=' + perPage)

    }
}

export const goodsAdd = {
    fetch(data) {
        return request('post', '/goods', data)
    }
}

export const goodsDelete = {
    fetch(goodsCode) {
        return request('delete', '/goods/' + goodsCode)
    }
}

export const goodsUpdate = {
    fetch(updateData, goodsCode) {
        return request('put', '/goods/' + goodsCode, updateData)
    }
}
<template style="margin-top:50%;">
<main style="font-family: Arial, Helvetica, sans-serif; margin:24px; margin-top:70%;">
<h1>Invoice Generator</h1>
<div  style="display:flex;gap:24px;">
<div style="flex:1;">
<InvoiceForm @saved="fetchInvoices" />
</div>
<div style="width:500px;">
<InvoiceList :invoices="invoices" style="overflow: scroll; max-height: 800px;"/>
</div>
</div>
</main>
</template>


<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import InvoiceForm from './components/InvoiceForm.vue'
import InvoiceList from './components/InvoiceList.vue'


const invoices = ref([])


async function fetchInvoices() {
try {
const res = await axios.get('/api/invoices')
invoices.value = res.data
} catch (e) {
console.error(e)
}
}


onMounted(fetchInvoices)
</script>
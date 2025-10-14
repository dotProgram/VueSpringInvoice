<template>
<main style="font-family: Arial, Helvetica, sans-serif; max-width:900px;margin:24px auto;">
<h1>Invoice Generator</h1>
<div style="display:flex;gap:24px;">
<div style="flex:1;">
<InvoiceForm @saved="fetchInvoices" />
</div>
<div style="width:360px;">
<InvoiceList :invoices="invoices" />
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
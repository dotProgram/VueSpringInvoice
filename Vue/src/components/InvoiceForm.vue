<template>
  <div>
    <h2>Create Invoice</h2>

    <div style="border:1px solid #ddd;padding:12px;border-radius:6px;">
      <div>
        <label>Customer name</label>
        <input v-model="customer.value" placeholder="Customer" />
      </div>

      <h3>Items</h3>
      <table style="width:100%;border-collapse:collapse;margin-bottom:12px;">
        <thead>
          <tr>
            <th style="text-align:left">Description</th>
            <th>Qty</th>
            <th>Unit Price</th>
            <th>Total</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, idx) in items" :key="idx">
            <td><input v-model="item.description" /></td>
            <td><input type="number" min="0" v-model.number="item.qty" style="width:72px" /></td>
            <td><input type="number" min="0" step="0.01" v-model.number="item.unitPrice" style="width:100px" /></td>
            <td>{{ (item.qty * item.unitPrice).toFixed(2) }}</td>
            <td><button @click="removeItem(idx)">Remove</button></td>
          </tr>
        </tbody>
      </table>

      <button @click="addItem">Add item</button>

      <div style="margin-top:12px">
        <strong>Subtotal: </strong>{{ subtotal.toFixed(2) }}
      </div>

      <div style="margin-top:12px;display:flex;gap:12px;">
        <button @click="saveInvoice">Save Invoice</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, computed } from 'vue'
import axios from 'axios'

const customer = reactive({ value: '' })
//const items = reactive([{ description: '', qty: 1, unitPrice: 0 }])
const items =reactive([{ description: '', qty: 1, unitPrice: 0 }]) 
function addItem() {console.log(items);
  items.push({ description: '', qty: 1, unitPrice: 0 })
  
}

function removeItem(idx) {
    console.log('Removing item at index:', idx);
  items.splice(idx, 1)
    console.log('Current items:', items);
}

const subtotal = computed(() =>
  items.reduce((s, it) => s + (it.qty || 0) * (it.unitPrice || 0), 0)
)

async function saveInvoice() {
  const payload = {
    customer: customer.value || 'Anonymous',
    items: items.map(i => ({
      description: i.description,
      qty: i.qty,
      unitPrice: i.unitPrice,
    })),
    subtotal: subtotal.value,
  }

  try {
    await axios.post('https://16106724752e.ngrok-free.app/api/invoices', payload)
    alert('Invoice saved!')
  } catch (e) {
    console.error(e)
    alert('Failed to save invoice')
  }
}
</script>
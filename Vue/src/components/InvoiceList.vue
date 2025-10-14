<template>
  <div>
    <h2 class="text-lg font-bold mb-3">Invoice List</h2>

    <div v-if="invoices && invoices.length">
      <div
        v-for="inv in invoices"
        :key="inv.id"
        class="border p-3 mb-3 rounded bg-gray-50"
      >
        <h3 class="text-md font-semibold">Customer: {{ inv.customer }}</h3>
        <p><strong>Subtotal:</strong> ₹{{ inv.subtotal.toFixed(2) }}</p>

        <!-- Safe rendering for items -->
        <details class="mt-2">
          <summary>
            Items ({{ inv.items?.length || 0 }})
          </summary>
          <ul v-if="inv.items && inv.items.length" class="ml-4 mt-1 list-disc">
            <li v-for="it in inv.items" :key="it.id">
              {{ it.description }} — {{ it.qty }} × ₹{{ it.unitPrice.toFixed(2) }}
            </li>
          </ul>
          <p v-else class="text-gray-500 italic">No items found</p>
        </details>
      </div>
    </div>

    <p v-else class="text-gray-500 italic">No invoices available</p>
  </div>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps({
  invoices: {
    type: Array,
    default: () => [], // Prevent null or undefined
  },
});
</script>

<style scoped>
summary {
  cursor: pointer;
  font-weight: 500;
}
</style>
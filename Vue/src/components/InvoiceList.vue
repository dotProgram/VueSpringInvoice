<template>
  <div class="p-4">
    <h2 class="text-xl font-bold mb-4" style="text-align: center;">Invoice List</h2>

    <!-- Loader -->
    <p v-if="loading" class="text-gray-500 italic">Loading invoices...</p>

    <!-- Show invoices if available -->
    <div v-else-if="invoices && invoices.length" style="text-align: center;">
      <div
        v-for="inv in invoices"
        :key="inv.id"
        class="border rounded-lg bg-gray-50 shadow-sm p-4 mb-4"
      >
        <h3 class="text-lg font-semibold mb-1">
          Customer: {{ inv.customer }}
        </h3>
        <p class="mb-2"><strong>Subtotal:</strong> ₹{{ inv.subtotal.toFixed(2) }}</p>

        <!-- Items List -->
        <details class="mb-2">
          <summary>Items ({{ inv.items?.length || 0 }})</summary>
          <ul v-if="inv.items && inv.items.length" class="ml-5 mt-2 list-disc">
            <li v-for="it in inv.items" :key="it.id">
              {{ it.description }} — {{ it.qty }} × ₹{{ it.unitPrice.toFixed(2) }}
            </li>
          </ul>
          <p v-else class="text-gray-500 italic mt-2">No items found</p>
        </details>

        <!-- Download PDF Button -->
        <button
          @click="downloadInvoicePdf(inv.id)"
          class="px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
        >
          📄 Download PDF
        </button>
      </div>
    </div>

    <!-- If no invoices -->
    <p v-else class="text-gray-500 italic">No invoices available</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

const invoices = ref([]);
const loading = ref(true);

/**
 * Fetch all invoices from Spring Boot backend
 */
const fetchInvoices = async () => {
  try {
    const response = await axios.get("https://16106724752e.ngrok-free.app/api/invoices");
    invoices.value = response.data || [];
  } catch (error) {
    console.error("Error fetching invoices:", error);
    alert("Failed to fetch invoices from backend.");
  } finally {
    loading.value = false;
  }
};

/**
 * Downloads the PDF of an invoice by calling Spring Boot backend.
 */
const downloadInvoicePdf = async (id) => {
  try {
    const response = await axios.get(`https://16106724752e.ngrok-free.app/api/invoices/${id}/pdf`, {
      responseType: "blob", // Important for binary data
    });

    const blob = new Blob([response.data], { type: "application/pdf" });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.href = url;
    link.download = `invoice-${id}.pdf`;
    document.body.appendChild(link);
    link.click();
    link.remove();
    window.URL.revokeObjectURL(url);
  } catch (error) {
    console.error("Error downloading invoice PDF:", error);
    alert("Failed to download invoice PDF");
  }
};

// Load invoices automatically on component mount
onMounted(() => {
  fetchInvoices();
});
</script>

<style scoped>
summary {
  cursor: pointer;
  font-weight: 500;
}
button {
  font-weight: 500;
}
</style>
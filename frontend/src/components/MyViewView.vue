<template>

    <v-data-table
        :headers="headers"
        :items="myView"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'MyViewView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "customerId", value: "customerId" },
                { text: "productId", value: "productId" },
                { text: "price", value: "price" },
                { text: "qty", value: "qty" },
                { text: "address", value: "address" },
                { text: "orderStatus", value: "orderStatus" },
                { text: "deliveryStatus", value: "deliveryStatus" },
            ],
            myView : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/myViews'))

            temp.data._embedded.myViews.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.myView = temp.data._embedded.myViews;
        },
        methods: {
        }
    }
</script>



const axios = require('axios');

axios.get('/catalog/filter', {
    params: {
        // ID: 12345
        category: $('#category option:selected').val()
    }
})
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        })
        .finally(function () {
            // always executed
        });

// Want to use async/await? Add the `async` keyword to your outer function/method.
async function getCatalog() {
    try {
        const response = await axios.get('/catalog/filter');
        console.log(response);
    } catch (error) {
        console.error(error);
    }
}


//$(document).ready(function () {
//   $("#category").change(function(){
//     getCatalog();  
//   })
//});

const axios = require('axios');

// Make a request for a user with a given ID
axios.get('/catalog')
  .then(function (response) {
    // handle success
    console.log(response);
  })
  .catch(function (error) {
    // handle error
    console.log(error);
  })
  .finally(function () {
    // always executed
  });
  
  async function getUser() {
  try {
    const response = await axios.get('/catalog');
    console.log(response);
  } catch (error) {
    console.error(error);
  }
}
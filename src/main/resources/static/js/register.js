var myform = document.querySelector("#myform");

        myform.addEventListener('submit', function(e) {
            if (!validation()) {
                e.preventDefault();
            }
        });

        function validation() {
            var success = true;

            var name = document.querySelector("#fullname").value.trim();
            var name_error = document.querySelector("#name_error");

            var number = document.querySelector("#mobileNumber").value.trim();
            var num_error = document.querySelector("#num_error");

            var guardian_number = document.querySelector("#guardianMobile").value.trim();
            var guar_num_error = document.querySelector("#guar_num_error");

            
            var zip_code = document.querySelector("#zipCode").value.trim();
            var zipCode_error = document.querySelector("#zipCode_error");



            // Validate name
            if (!/^[a-zA-Z]+$/.test(name)) {
                success = false;
                name_error.textContent = "Name can only contain letters and spaces";
            } else {
                name_error.textContent = "";
                
            }

            // Validate mobileNumber
            if (!/^[0-9]+$/.test(number)) {
                success = false;
                num_error.textContent = "Enter a valid mobile number";
            } else if (number.length !== 10) {
                success = false;
                num_error.textContent = "Enter a 10-digit mobile number";
            } else {
                num_error.textContent = "";
                
            }

            // Validate guardianMobile
            if (!/^[0-9]+$/.test(guardian_number)) {
                success = false;
                guar_num_error.textContent = "Enter a valid mobile number";
            } else if (guardian_number.length !== 10) {
                success = false;
                guar_num_error.textContent = "Enter a 10-digit mobile number";
            } else if (guardian_number === number) {
                success = false;
                guar_num_error.textContent = "Cannot be the same as Mobile Number";
            } else {
                guar_num_error.textContent = "";
                
            }

            //validate zipcode
            if (!/^[0-9]+$/.test(zip_code)) {
                success = false;
                zipCode_error.textContent = "Enter a valid zipcode(Number)";
            } else if (zip_code.length !== 6) {
                success = false;
                zipCode_error.textContent = "Enter a 6-digit zipcode";
            }  else {
              zipCode_error.textContent = "";
                
            }



  var city = document.querySelector("#city").value.trim();
  var city_error = document.querySelector("#city_error");

  // Validate city
  if (!/^[a-zA-Z]+$/.test(city)) {
    success = false;
    city_error.textContent = "City can only contain letters";
  } else {
    city_error.textContent = "";
  }


  var state = document.querySelector("#state").value.trim();
  var state_error = document.querySelector("#state_error");

  // Validate state
  if (!/^[a-zA-Z]+$/.test(state)) {
    success = false;
    state_error.textContent = "state can only contain letters";
  } else {
    state_error.textContent = "";
  }
  var country = document.querySelector("#country").value.trim();
  var country_error = document.querySelector("#country_error");

  // Validate country
  if (!/^[a-zA-Z]+$/.test(country)) {
    success = false;
    country_error.textContent = "country can only contain letters";
  } else {
    country_error.textContent = ""; guardianName
  }

  var guardianName = document.querySelector("#guardianName").value.trim();
  var guardian_error = document.querySelector("#guardian_error");

  // Validate guardianName
  if (!/^[a-zA-Z]+$/.test(guardianName)) {
    success = false;
    guardian_error.textContent = "guardianName can only contain letters";
  } else {
    guardian_error.textContent = "";
  }


 //validate email
  var email = document.querySelector("#email").value.trim();
  var email_error = document.querySelector("#email_error");


const validateEmail = (email) => {
  return String(email)
    .toLowerCase()
    .match(
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    );
};



if (email === "") {
  success = false;
  email_error.textContent = "Email is required";
} else if (!validateEmail(email)) {
  success = false;
  email_error.textContent = "Enter a valid email";
} else {
  email_error.textContent = "";
}





return success;


}

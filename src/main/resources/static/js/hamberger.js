    
   
  
    // Function to generate menu items

  
      // Loop through menuItems array and create menu items
    
  
    // Toggle mobile menu visibility
   
      function toggleMenu() {
        const mobileMenu = document.querySelector('#items');
        mobileMenu.classList.toggle('active');
        
         items.addEventListener('mouseleave', () => {
        mobileMenu.classList.remove('active');
    });
    }
  
/*    toggleButton.addEventListener('mouseover', () => {
        items.classList.add('active');
    });*/

   

        $(document).ready(function(){



          $(window).resize(function(){
            var dbNavHeight = $('#db-navbar').css('height');
            $('#side-panel').css('top',dbNavHeight);
            $('#db-content').css('top',dbNavHeight);
          });

          $('.employessListWrapper').click( function() {

            $('.collapse').collapse('hide');
          });
          
        });
        


        function validateGroupInputsByClassName(c) {
          var elems = document.getElementsByClassName(c);
          var valid = true;
          var anyInput = false;
  
          for (var i = 0; i < elems.length; i++) {
  
              var el = elems[i];
              var val = el.value;
              if (val.length > 0) {
                  anyInput = true;
              }
  
              if (anyInput && val.length < 1) {
                  valid  = false;
                  el.setAttribute("style", "border : 1px solid red");
              } else {
                  el.setAttribute("style", "border : 1px solid gray");
              }
          }
          return valid;
      }

      function validateFormByClassName(c) {
        var elems = document.getElementsByClassName(c);
        var valid = true;

        for (var i = 0; i < elems.length; i++) {

            var el = elems[i];
            var val = el.value;


            if (val.length < 1) {
                valid  = false;
                el.setAttribute("style", "border : 1px solid red");
            } else {
                el.setAttribute("style", "border : 1px solid gray");
            }
        }
        return valid;
    }

    function dashBoardFunctions() {
          
        var disableScroll = false;

        var dbNavHeight = $('#db-navbar').css('height');
        $('#side-panel').css('top',dbNavHeight );
        $('#db-content').css('top',dbNavHeight);


        $('#v-pills-tab > li').click(function () {
          $('#Employees-h').collapse("hide");
          $('#Tools-h').collapse("hide");
        });
  
        $('#v-pills-tools-tab').click(function () {
          $('#Tools-h').collapse("toggle");
        });
          
        $('#v-pills-employees-tab').click(function () {
          $('#Employees-h').collapse("toggle");
        });

        $('#chat-minimize').click(function () {
          $('#chat-icon').toggle();
          $('#chatWrapper').toggle();
        });
  
        $('#chat-icon').click(function () {
          $('#chat-icon').toggle();
          $('#chatWrapper').toggle();
        });
  
        $('#navbar-toggler').click(function () {
  
          $('#side-panel').toggle();
          $('#side-panel-support').toggle();


  
          disableScroll = !disableScroll;
          if (disableScroll) {
  
            $('#tabWrapper').css('overflow', 'hidden');
          } else {
  
            $('#tabWrapper').css('overflow', 'auto');
          }

  
        });
  
      }
  
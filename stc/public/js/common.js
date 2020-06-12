$(document).ready(() => {
  const { swal } = window;

  $('form.prevent-submit-form').submit((e) => {
    e.preventDefault();
  });

  const showLoading = () => {
    $('.main-loader').show();
  };

  const hideLoading = () => {
    $('.main-loader').hide();
  };

  function onClickBtnLogout() {
    swal({
      title: 'Logout?',
      text: 'Are you sure to logout this session?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonClass: 'btn btn-danger',
      confirmButtonText: 'Yes, logout!',
      closeOnConfirm: false,
      showLoaderOnConfirm: true
    },
    () => {
      $.ajax({
        method: 'GET',
        url: `${window.prefixPath}/api/auth`,
        data: {
          action: 'LOGOUT'
        },
        success() {
          window.location.reload();
        },
        error(err) {
          console.log(err);
          const json = err.responseJSON;
          swal({
            title: 'Error',
            text: `Error code: ${json.error} - Message: ${json.value}`,
            type: 'error'
          });
        }
      });
    });
  }

  const copyToClipboard = (str) => {
    const el = document.createElement('textarea');
    el.value = str;
    document.body.appendChild(el);
    el.select();
    document.execCommand('copy');
    document.body.removeChild(el);
  };

  window.onClickBtnLogout = onClickBtnLogout;
  window.copyToClipboard = copyToClipboard;
  window.showLoading = showLoading;
  window.hideLoading = hideLoading;
});


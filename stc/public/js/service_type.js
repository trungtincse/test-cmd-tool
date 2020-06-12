$(document).ready(() => {
  const { swal } = window;

  $('#dt-service-types').DataTable();

  function onClickDeleteServiceType(ele, name) {
    swal({
      title: 'Delete this item?',
      text: `You will not be able to recover service ${name}!`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonClass: 'btn btn-danger',
      confirmButtonText: 'Yes, delete it!',
      closeOnConfirm: false,
      showLoaderOnConfirm: true
    },
    () => {
      $.ajax({
        method: 'POST',
        url: `${window.prefixPath}/api/service`,
        data: {
          action: 'DELETE',
          service_type_name: name
        },
        success(data) {
          console.log(data);
          swal({
            title: 'Deleted',
            text: `Service ${name} has been deleted`,
            type: 'success'
          }, () => {
            window.location.reload();
          });
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

  function onClickDetail(serviceName) {
    window.location.href = `${window.prefixPath}/service/${serviceName}`;
  }

  // Export
  window.onClickDeleteServiceType = onClickDeleteServiceType;
  window.onClickDetail = onClickDetail;

});

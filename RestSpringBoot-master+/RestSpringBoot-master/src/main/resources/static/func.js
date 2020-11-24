let allRoles;
let allUsers;

getAllRoles();
getAllUsers();
getCurrent();

// Poluchaem tekuschego usera
function getCurrent() {
    $.ajax({
        url: "/getCurrentUser",
        type: "GET",
        dataType: "json"
    }).done((msg) => {
        let user = JSON.parse(JSON.stringify(msg));
        $("#current-user-table tbody").empty().append(
            $("<tr>").append(
                $("<td>").text(user.id),
                $("<td>").text(user.name),
                $("<td>").text(user.email),
                $("<td>").text(user.roles)
            ));
    }).fail(() => {
        alert("Error Get All Users!")
    })
}

$('[href="#v-pills-user"]').on('show.bs.tab', (e) => {
    getCurrent();
})

// Poluchaem role
function getAllRoles() {
    $.ajax({
        url: "/rest/admin/roles",
        type: "GET",
        dataType: "json"
    }).done((msg) => {
        allRoles = JSON.parse(JSON.stringify(msg));
    })
}

// vse usery zapolnyaem tablicu
function getAllUsers() {
    $.ajax({
        url: "/rest/admin/users",
        type: "GET",
        dataType: "json"
    }).done((msg) => {
        allUsers = JSON.parse(JSON.stringify(msg));
        $("#all-users-table tbody").empty();
        $.each(allUsers, (i, user) => {
            $("#all-users-table tbody").append(
                $("<tr>").append(
                    $("<td>").text(user.id),
                    $("<td>").text(user.name),
                    $("<td>").text(user.email),
                    $("<td>").text(user.roles),

                    // mapim na knopki v index.html
                    $("<td>").append("<button type='button' data-toggle='modal' class='btn-info btn'" +
                        "data-target='#editUserModal' data-user-id='" + user.id + "'>Edit</button>"),
                    $("<td>").append("<button type='button' data-toggle='modal' class='btn btn-danger'" +
                        "data-target='#deleteUserModal' data-user-id='" + user.id + "'>Delete</button>")
                )
            );
        });
    });
}

// mapim zapolnenie na knopku
$('[href="#v-pills-admin"]').on('show.bs.tab', (e) => {
    getAllUsers();
})

// modalka edit user tabla + v konce update
$("#editUserModal").on('show.bs.modal', (e) => {
    let userId = $(e.relatedTarget).data("user-id");

    $.ajax({
        url: "/rest/admin/users/" + userId,
        type: "GET",
        dataType: "json"
    }).done((msg) => {

        let user = JSON.parse(JSON.stringify(msg));

        $("#idInputEdit").empty().val(user.id);
        $("#firstNameInputEdit").empty().val(user.name);
        $("#emailInputEdit").empty().val(user.email);
        $("#rolesInputEdit").empty();
        $.each(allRoles, (i, role) => {
            $("#rolesInputEdit").append(
                $("<option>").text(role)
            )
        });
        $("#rolesInputEdit").val(user.roles);

        $("#buttonEditSubmit").on('click', (e) => {
            e.preventDefault();

            let editUser = {
                id: $("#idInputEdit").val(),
                name: $("#firstNameInputEdit").val(),
                password: $("#passwordInputEdit").val(),
                email: $("#emailInputEdit").val(),
                roles: $("#rolesInputEdit").val()
            }

            $.ajax({
                url: "/rest/admin/users",
                type: "PUT",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(editUser)
            }).done((msgUpdate) => {
                $("#editUserModal").modal('hide');
                getAllUsers();
            })
        })
    });
});

// udalyaem usera + update table
$("#deleteUserModal").on('show.bs.modal', (e) => {
    let userId = $(e.relatedTarget).data("user-id");

    $.ajax({
        url: "/rest/admin/users/" + userId,
        type: "GET",
        dataType: "json"
    }).done((msg) => {
        let user = JSON.parse(JSON.stringify(msg));

        $("#idInputDelete").empty().val(user.id);
        $("#firstNameInputDelete").empty().val(user.name);
        $("#emailInputDelete").empty().val(user.email);
        $("#rolesInputDelete").empty();
        $.each(allRoles, (i, role) => {
            $("#rolesInputDelete").append(
                $("<option>").text(role)
            )
        });
        $("#rolesInputDelete").val(user.roles);

        $("#buttonDeleteSubmit").on('click', (e) => {
            e.preventDefault();

            $.ajax({
                url: "/rest/admin/users/" + userId,
                type: "DELETE",
                dataType: "text"
            }).done((deleteMsg) => {
                $("#deleteUserModal").modal('hide');
                getAllUsers();
            })
        })
    });
})

// perehod na dobavlenie newUser script
$('[href="#newUser"]').on('show.bs.tab', (e) => {
    $(() => {
        $("#firstNameInputNew").empty().val("");
        $("#passwordInputNew").empty().val("");
        $("#emailInputNew").empty().val("");
        $("#rolesInputNew").empty().val("");
        $.each(allRoles, (i, role) => {
            $("#rolesInputNew").append(
                $("<option>").text(role)
            )
        });
    })
})

// privyazka k knopke dobavleniya usera v html
$("#buttonInputNewSubmit").on('click', (e) => {
    e.preventDefault();

    let newUser = {
        name: $("#firstNameInputNew").val(),
        password: $("#passwordInputNew").val(),
        email: $("#emailInputNew").val(),
        roles: $("#rolesInputNew").val()
    }

    $.ajax({
        url: "/rest/admin/users",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(newUser)
    }).done((msgSave) => {
        getAllUsers();
        $('#AdminTabs a[href="#usersTable"]').tab('show');
    })
})

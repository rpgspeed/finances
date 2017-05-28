/**
 * Created by Rpg on 26/05/2017.
 */
    $(document).ready(function(){
        $('#stockTable').DataTable(
            {
                "order": [[ 4, "desc" ]]
            }

        );
    });

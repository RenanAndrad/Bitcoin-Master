

import android.content.DialogInterface

interface IAlertConfirm {
     fun positive(dialog: DialogInterface, id: Int)

     fun negative(dialog: DialogInterface, id: Int)
}
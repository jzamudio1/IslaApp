package com.jzamudio.isla21410.util

import android.graphics.Path
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.adapter.EmpresasAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import kotlinx.coroutines.launch

class ClickListner {

    companion object Todos {
        var Alojamiento = false
        var Servicio = false
        var Comida = false
        var CargaBBDD = false

    }


}

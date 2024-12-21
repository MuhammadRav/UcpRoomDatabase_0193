package com.example.week11.dependenciesInjection

import android.content.Context
import com.example.week11.data.database.KrsDatabase
import com.example.week11.repository.LocalRepoDosen
import com.example.week11.repository.LocalRepoMatakuliah
import com.example.week11.repository.RepoDosen
import com.example.week11.repository.RepoMatakuliah

interface InterfaceContainerApp{
    val repoDosen: RepoDosen
    val repoMatakuliah: RepoMatakuliah
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repoDosen: RepoDosen by lazy {
        LocalRepoDosen(KrsDatabase.getDatabase(context).dosenDao())
    }
    override val repoMatakuliah: RepoMatakuliah by lazy {
        LocalRepoMatakuliah(KrsDatabase.getDatabase(context).matakuliahDao())
    }
}
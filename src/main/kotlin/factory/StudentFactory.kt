package factory

import student.Student

interface StudentFactory<T : Student> {
    fun create(builder: T.() -> Unit): T
//    fun create(props: Map<String, *>): T
}
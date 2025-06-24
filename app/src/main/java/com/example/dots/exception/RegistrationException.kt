package com.example.dots.exception

class RegistrationException(val fieldErrors: Map<String, String>) : Exception("Validasi gagal")

package com.spring.activity.config;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SwaggerCustomController {

    @GetMapping(
            value = "/swagger-ui-custom.html",
            produces = MediaType.TEXT_HTML_VALUE
    )
    @ResponseBody
    public String swaggerCustom() {

        return """
                <!DOCTYPE html>
                <html lang="es">

                <head>
                    <meta charset="UTF-8">
                    <title>API Documentation</title>

                    <link rel="stylesheet"
                          href="/swagger-ui/swagger-ui.css">

                    <style>

                        /* Ocultar CURL */
                        .swagger-ui .curl-command {
                            display: none !important;
                        }

                        /* Ocultar Schemas */
                        .swagger-ui section.models {
                            display: none !important;
                        }

                        /* Ocultar documentación de responses */
                        .swagger-ui .responses-table:not(.live-responses-table) {
                            display: none !important;
                        }

                    </style>
                </head>

                <body>

                <div id="swagger-ui"></div>

                <script src="/swagger-ui/swagger-ui-bundle.js"></script>
                <script src="/swagger-ui/swagger-ui-standalone-preset.js"></script>

                <script>

                    window.onload = function () {

                        window.ui = SwaggerUIBundle({

                            url: "/v3/api-docs",

                            dom_id: "#swagger-ui",

                            deepLinking: true,

                            presets: [
                                SwaggerUIBundle.presets.apis,
                                SwaggerUIStandalonePreset
                            ],

                            layout: "StandaloneLayout",

                            tryItOutEnabled: true
                        });


                        /*
                         * Limpia elementos que Swagger
                         * genera dinámicamente.
                         */
                        function limpiarSwagger() {

                            // =====================================
                            // CURL
                            // =====================================

                            document
                                .querySelectorAll(".curl-command")
                                .forEach(elemento => {
                                    elemento.style.display = "none";
                                });


                            // =====================================
                            // SCHEMAS / MODELS
                            // =====================================

                            document
                                .querySelectorAll("section.models")
                                .forEach(elemento => {
                                    elemento.style.display = "none";
                                });


                            // =====================================
                            // RESPONSES DE DOCUMENTACIÓN
                            // =====================================

                            document
                                .querySelectorAll(".responses-table")
                                .forEach(tabla => {

                                    if (!tabla.classList.contains(
                                            "live-responses-table"
                                        )) {

                                        tabla.style.display = "none";
                                    }

                                });


                            // =====================================
                            // TITULO "Responses"
                            // =====================================

                            document
                                .querySelectorAll("h4")
                                .forEach(titulo => {

                                    if (
                                        titulo.textContent.trim()
                                            === "Responses"
                                    ) {

                                        titulo.style.display = "none";
                                    }

                                });


                            // =====================================
                            // RESPONSE HEADERS
                            // =====================================

                            document
                                .querySelectorAll("h5")
                                .forEach(titulo => {

                                    if (
                                        titulo.textContent
                                            .trim()
                                            .toLowerCase()
                                            === "response headers"
                                    ) {

                                        titulo.style.display = "none";

                                        if (titulo.nextElementSibling) {

                                            titulo.nextElementSibling
                                                .style.display = "none";
                                        }
                                    }

                                });

                        }


                        /*
                         * Swagger modifica el DOM constantemente,
                         * especialmente después de Execute.
                         *
                         * Por eso observamos los cambios.
                         */
                        const observer = new MutationObserver(
                            limpiarSwagger
                        );

                        observer.observe(
                            document.body,
                            {
                                childList: true,
                                subtree: true
                            }
                        );

                        limpiarSwagger();
                    };

                </script>

                </body>

                </html>
                """;
    }
}
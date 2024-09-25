El objetivo de esta kata es entender la diferencia entre la gestión de errores con excepciones y con el uso del tipo either. Para ello, vamos a implementar un caso de uso basado en un sistema de gestión de pedidos de comida online tipo just-eat.

El caso de uso es añadir un nuevo item a la carta de un restaurante y debe cumplir con las siguientes reglas:
- Reglas de validación
    - El precio debe ser mayor que 0
    - El precio debe ser menor que 100
    - El nombre no puede estar vacío
    - El nombre solo puede tener caracteres alfanuméricos y guiones
    - El nombre no puede tener más de 50 caracteres
    - La categoría debe ser una de las siguientes: "starters", "main", "desserts", "drinks"
- Reglas de negocio
    - El restaurante debe existir y estar abierto
    - No se pueden añadir items a una categoría que tenga más de 10 items


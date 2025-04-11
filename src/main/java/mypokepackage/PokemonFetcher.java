package mypokepackage;

import java.io.IOException;

/**
 * Interface for fetching Pokémon data using various backends (REST, GraphQL).
 * This interface avoids enforcing any specific data model shape.
 */
public interface PokemonFetcher {

    /**
     * Fetches the raw JSON or GraphQL response for the given Pokémon name.
     *
     * @param name The name of the Pokémon (e.g., "pikachu")
     * @return A raw JSON string containing the response data
     * @throws IOException if a request error occurs
     *
     * 📚 OkHttp: https://square.github.io/okhttp/
     * 📚 GraphQL over HTTP: https://graphql.org/learn/serving-over-http/
     */
    String fetchRaw(String name) throws IOException;

    /**
     * Processes and extracts information from a raw JSON or GraphQL response.
     * May print directly, or format and return info as a string.
     *
     * @param rawResponse The raw response string (e.g., JSON)
     * @return A user-friendly summary of the Pokémon (e.g., "Pikachu (#25) has 35 HP.")
     *
     * 📚 Gson parsing: https://github.com/google/gson/blob/master/UserGuide.md
     * 📚 GraphQL tooling (Apollo or manual): https://www.apollographql.com/docs/kotlin/v3/java/intro/
     */
    String summarizeFromRaw(String rawResponse);

    /**
     * Fetches and summarizes a Pokémon in one call.
     *
     * @param name The name of the Pokémon
     * @return A summary string (e.g., "Bulbasaur (#1) has 45 HP.")
     * @throws IOException if an error occurs during fetching
     */
    default String summarizePokemon(String name) throws IOException {
        return summarizeFromRaw(fetchRaw(name));
    }

    /**
     * Prints the result of {@link #summarizePokemon(String)} to standard output.
     *
     * @param name The name of the Pokémon
     * @throws IOException if an error occurs
     */
    default void fetchAndPrint(String name) throws IOException {
        System.out.println(summarizePokemon(name));
    }
}

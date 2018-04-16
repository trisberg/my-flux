FROM projectriff/java-function-invoker:0.0.6-snapshot
ARG FUNCTION_JAR=/functions/my-flux-0.0.1.jar
ARG FUNCTION_HANDLER=com.springdeveloper.streams.demo.SourceFunction
ADD target/my-flux-0.0.1.jar $FUNCTION_JAR
ENV FUNCTION_URI file://${FUNCTION_JAR}?handler=${FUNCTION_HANDLER}

import threading
class DoubleCheckSingleton:
    _instance =None
    _lock= threading.Lock()
    
    def __init__(self):
        if DoubleCheckSingleton._instance is not None:
            raise Exception("Use get_instance() instead.")
    
    def get_instance():
        if DoubleCheckSingleton._instance is None:
            with DoubleCheckSingleton._lock:
                if DoubleCheckSingleton._instance is None:
                    DoubleCheckSingleton._instance = DoubleCheckSingleton()
        return DoubleCheckSingleton._instance
                    